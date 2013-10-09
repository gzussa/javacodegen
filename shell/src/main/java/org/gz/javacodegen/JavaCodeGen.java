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

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.args.Parser;
import org.gz.javacodegen.core.AbstractPlugin;
import org.gz.javacodegen.core.logger.SystemPrint;
import org.gz.javacodegen.plugin.springroojpa.SpringRooJpaPlugin;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * Main class. This class delegate actions based on passed arguments
 * @author asansom, gzussa
 *
 */
public class JavaCodeGen {

	static Logger logger = LogManager.getLogger(JavaCodeGen.class.getName());
	
	//Basic constructor
	public JavaCodeGen(){}	

	public static void main(String[] args){
        Parser parser = new Parser();
        Integer checkCode = parser.loadAndCheckArgs(args); 
      
        //Display arguments
        SystemPrint.debug(parser.isDebug(), "Help : "+parser.isHelp());
        SystemPrint.debug(parser.isDebug(), "Version : "+parser.isVersion());
        SystemPrint.debug(parser.isDebug(), "Plugin : "+parser.getPlugin());
        SystemPrint.debug(parser.isDebug(), "Inputs : "+parser.getInputs());
        SystemPrint.debug(parser.isDebug(), "Output : "+parser.getOutput());
        
        PluginFactory pluginFactory = new PluginFactory();
        AbstractPlugin plugin = null;
        switch (checkCode){
        case -1:
        	//Print error usage details and exit with -1 code
        	System.exit(-1);
        	break;
        case 0:
        	//Print plugin usage details
        	plugin = pluginFactory.getPluginInstance(parser.getPlugin());
        	plugin.printUsage();
        	break;
        case 1:
        	//Print tool usage detail Version
        	break;
        case 2:
        	//Print plugin version
        	plugin = pluginFactory.getPluginInstance(parser.getPlugin());
        	plugin.printVersion();
        	break;
        case 3:
        	//Print tool version
        	printToolVersion();
        	break;
        case 4:
        	//Print plugin list
        	pluginFactory.printPluginList();
        	break;
        case 5:
        	//Execute plugin
        	plugin = pluginFactory.getPluginInstance(parser.getPlugin());
        	for(Iterator<String> inputFileIterator = parser.getInputs().iterator(); inputFileIterator.hasNext();){
        		String currentInputFile = inputFileIterator.next();
        		File file = new File(currentInputFile);
        		plugin.run(file.getAbsolutePath(), parser.getOutput());
        	}
        	break;
        }
        //Exit with 0 code
	}
	
	/**
	 * Print tool Version
	 */
	private static void printToolVersion() {
		SystemPrint.info(Constants.VERSION);
	}
}
