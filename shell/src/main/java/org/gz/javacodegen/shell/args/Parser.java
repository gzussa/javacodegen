/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Gregory
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package org.gz.javacodegen.shell.args;

import java.util.List;

import org.gz.javacodegen.core.logger.LogConfigurator;
import org.gz.javacodegen.core.logger.LogHelper;
import org.gz.javacodegen.shell.Constants;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * This class validates option and arguments passed to the tool
 * @author asansom, gzussa
 *
 */
public class Parser {
	
	private static LogHelper logger = LogConfigurator.getLogger(Parser.class.getName());

	/**
	 * This option is used to display debug information in stdout
	 */
	@Option(name = "-x", required = false, aliases = {"--debug"}, usage = "Run Command in debug mode. This option can be used in every usage possible")
    private boolean debug;
	
	/**
	 * This option is used to display help either about the tool or about a plugin if -p option is specified
	 */
	@Option(name = "-h", required = false, aliases = {"--help"}, usage = "Display tool or plugin help")
    private boolean help;
	
	/**
	 * This option is used to display the version of the tool or the version of the plugin if -p option is specified
	 */
	@Option(name = "-v", required = false, aliases = {"--version"}, usage = "Display tool or plugin Version")
    private boolean version;
	
	/**
	 * This option is used to display the list of available plugins
	 */
	@Option(name = "-l", required = false, aliases = {"--list"}, usage = "Display list of available plugins")
    private boolean list;
	
	/**
	 * This option is used to specified which plugin to use while evaluating input files
	 */
	@Option(name = "-p", aliases = {"--plugin"}, required = false, usage = "Plugin to use")
	private String plugin;
	
	/**
	 * This argument is used to specify where to generate files. See plugin details in order to understand how the plugin is going to use it.
	 */
	@Argument(index = 0, required = false, usage="Base output")
	private String output;
	
	/**
	 * Other arguments are used to specify input files to evaluate.
	 */
	@Argument(index = 1, required = false, multiValued = true, usage="Input file(s)")
	private List<String> inputs;
    
	/**
	 * Simple constructor. By default Version, debug and help option are disabled
	 */
    public Parser(){
    	version = false;
    	debug = false;
    	help = false;
    	plugin = null;
    	inputs = null;
    	output = null;
    }

	public boolean isDebug() {
		return debug;
	}

	public boolean isHelp() {
		return help;
	}
	
	public boolean isVersion() {
		return version;
	}
	
	public boolean isList() {
		return list;
	}

	public String getPlugin() {
		return plugin;
	}

	public String getOutput() {
		return output;
	}
	
	public List<String> getInputs() {
		return inputs;
	}
	
	/**
	 * This method checks if options and arguments sequences are valid. Although, this method doesn't check if values entered are correct but if options and arguments used as expected.
	 * We check the form, not the content!
	 * @param args to get and check
	 * @return code (number) based on the usage.
	 * 	-1 : An error occurred. Print Error Usage
	 * 	0  : For plugin usage details action
	 * 	1  : For tool usage details action
	 *  2  : For plugin version detail action
	 *  3  : For tool version detail action
	 *  4  : For plugin list action
	 *  5  : For plugin execution action
	 */
	public Integer loadAndCheckArgs(String[] args) {
		//Initialise Arg4J
		CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);
        
        try {
            // parse the arguments.
            parser.parseArgument(args);
            
            //Debug option can be set in any case
            LogConfigurator.initLogger(debug);
            
            //If help option and potentially plugin option are set then all other option should be unset (except debug).
            if(help && !version && !list && output == null && inputs == null){
            	if(plugin != null){
            		//print plugin usage details
            		return 0;
            	}else{
            		//Print tool usage details
            		printUsage(parser);
            		return 1;
            	}
            }
     
            //If version option and potentially plugin option are set then all other option should be unset (except debug).
            if(version && !help  && !list && output == null && inputs == null){
            	if(plugin != null){
            		//print plugin usage details
            		return 2;
            	}else{
            		//Print tool version info
            		return 3;
            	}
            }
            
            //If list option is used and no other options or arguments are set
            if(list && !version && !help && plugin == null && output == null && inputs == null){
            	return 4;
            }
            
            //If version, help and list options are not used then plugin, output and input should be set
            if(!version && !help && !list && plugin != null && output != null && inputs != null){
            	return 5;
            }
            
            //If none of the above conditions have been selected then it means that the tool wasn't used correctly
            printErrorUsage(parser);
            
        }catch(CmdLineException e) {
        	printErrorUsage(parser);
        }
        return -1;
	}

	/**
	 * print usage description and example in stdout
	 * @param parser parser used to fetch arguments with arg4j
	 */
	private void printUsage(CmdLineParser parser) {
		logger.info(Constants.OUTPUT);
		parser.printUsage(System.out);
		logger.info(Constants.USAGE_EXAMPLES);		
	}
	
	/**
	 * print usage description and example in stderr
	 * @param parser parser used to fetch arguments with arg4j
	 */
	private void printErrorUsage(CmdLineParser parser) {
		logger.error(Constants.OUTPUT);
		parser.printUsage(System.err);
		logger.error(Constants.USAGE_EXAMPLES);		
	}

}
