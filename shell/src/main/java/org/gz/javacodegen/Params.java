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
package org.gz.javacodegen;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.core.logger.SystemPrint;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * This class validates option and arguments passed to the tool
 * @author asansom, gzussa
 *
 */
public class Params {
	
	private static Logger logger = LogManager.getLogger(GeneratorFactory.class.getName());

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
    public Params(){
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
	 * @return true if no issue occurs otherwise false.
	 * TODO return a code (number) based on the usage. In the main class, add a switch case that would delegate logic to other specialized classes.
	 */
	public boolean loadAndCheckArgs(String[] args) {
		//Initialise Arg4J
		CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);
        
        try {
            // parse the arguments.
            parser.parseArgument(args);
            
            //Debug option can be set in any case.
            
            //If help option and potentially plugin option are set then all other option should be unset (except debug).
            if(help && !version && !list && output == null && inputs == null){
            	if(plugin != null){
            		//TODO print plugin usage details
            		return true;
            	}else{
            		//Print tool usage details
            		printUsage(parser);
            		return true;
            	}
            }
     
            //If version option and potentially plugin option are set then all other option should be unset (except debug).
            if(version && !help  && !list && output == null && inputs == null){
            	if(plugin != null){
            		//TODO print plugin usage details
            		return true;
            	}else{
            		//Print tool version info
            		printVersion();
            		return true;
            	}
            }
            
            //If list option is used and no other options or arguments are set
            if(list && !version && !help && plugin == null && output == null && inputs == null){
            	printPluginList();
            	return true;
            }
            
            //If version, help and list options are not used then plugin, output and input should be set
            if(!version && !help && !list && plugin != null && output != null && inputs != null){
            	return true;
            }
            
            //If none of the above conditions have been selected then it means that the tool wasn't used correctly
            printErrorUsage(parser);
            
        }catch(CmdLineException e) {
        	printErrorUsage(parser);
        }
        return false;
	}
	
	/**
	 * Print this of available plugins
	 * TODO Move this function outside of this class and add more info to what's displayed.
	 * Also fetch info dynamically
	 */
	private void printPluginList() {
		SystemPrint.info(Constants.PLUGINS);
	}

	/**
	 * Print tool Version
	 * TODO Move this function outside of this class and add more info to what's displayed
	 */
	private void printVersion() {
		SystemPrint.info(Constants.VERSION);
	}

	/**
	 * print usage description and example in stdout
	 * @param parser parser used to fetch arguments with arg4j
	 */
	private void printUsage(CmdLineParser parser) {
		SystemPrint.info("OPTIONS:");
		parser.printUsage(System.out);
		SystemPrint.info(Constants.USAGE_EXAMPLES);		
	}
	
	/**
	 * print usage description and example in stderr
	 * @param parser parser used to fetch arguments with arg4j
	 */
	private void printErrorUsage(CmdLineParser parser) {
		SystemPrint.error("OPTIONS:");
		parser.printUsage(System.err);
		SystemPrint.error(Constants.USAGE_EXAMPLES);		
	}

}
