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
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.core.AbstractGenerator;
import org.gz.javacodegen.core.logger.SystemPrint;
import org.gz.javacodegen.plugin.springroojpa.SpringRooJpaGenerator;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

//TODO Ad Javadoc
public class JavaCodeGen {

	static Logger logger = LogManager.getLogger(JavaCodeGen.class.getName());
	
	//TODO Add Javadoc to all attributes
	private List<File> plugins;
	private List<String> autorizedType;
	private String projectRootTest;
	private String projectRootMain;
	private	String projectType;
	private	List<File> configurationFile;
//	private Params params;
	
	public JavaCodeGen(/*Params params*/){
		plugins = null;
		autorizedType = null;
		projectRootTest = null;
		projectRootMain = null;
		projectType = null;
		configurationFile = null;
//		this.params = params;
	}	

	public static void main(String[] args){
        Params params = new Params();
        
        //First we check parameters
        if(!params.loadAndCheckArgs(args)){
        	System.exit(-1);
        }
        
        //Display arguments
        SystemPrint.debug(params.isDebug(), "Help : "+params.isHelp());
        SystemPrint.debug(params.isDebug(), "Version : "+params.isVersion());
        SystemPrint.debug(params.isDebug(), "Plugin : "+params.getPlugin());
        SystemPrint.debug(params.isDebug(), "Inputs : "+params.getInputs());
        SystemPrint.debug(params.isDebug(), "Output : "+params.getOutput());
        
        GeneratorFactory generatorFactory = new GeneratorFactory(params);
        generatorFactory.generate();
	}
	
//	private void init(String[] args){
//		CmdLineParser parser = new CmdLineParser(params);
//		
////		// Init plugins : list all plugins available
////		File pluginsFolder = new File("./src/plugins");
////		logger.debug("plugins folder : "+pluginsFolder.toString());
////		File[] listOfPlugins = pluginsFolder.listFiles(); 
////		plugins  = new ArrayList<File>();
////		logger.debug(listOfPlugins.length+" plugins found : ");
//		
////		autorizedType = new ArrayList<String>();
////		for (int i = 0; i < listOfPlugins.length; i++) 
////		{
////			if (listOfPlugins[i].isDirectory()) 
////			{
////				plugins.add(listOfPlugins[i]);
////				logger.debug("\t"+listOfPlugins[i].getName());
////				autorizedType.add(listOfPlugins[i].getName());
////			}
////		}
//		
//		
//		try {
//            parser.parseArgument(args);
//            
//            //Configure project Root
//            projectRootTest = params.getOutputTest();      
//            projectRootMain = params.getOutputMain();  
//            
////            //Configure project type
////            if(isAutorizedType(params.getProjectType())){
////            	projectType = params.getProjectType();
////                logger.info("Project type : "+projectType);
////            } else {
////            	logger.error("This project type does not existe.");
////            	System.exit(-1);
////            }
//            	
//            if (params.getXmlConfigFile() != null && params.getXmlFolder() != null){
//            	logger.error("You can't set both folder and file for the xml configuration files");
//            	System.exit(-1);
//            } else if(params.getXmlConfigFile() != null) {
//            	configurationFile = new ArrayList<File>();
//            	configurationFile.add(new File(params.getXmlConfigFile()));
//            } else if(params.getXmlFolder() != null){
//				File folder = new File(params.getXmlFolder());
//				File[] listOfFiles = folder.listFiles(); 
//				configurationFile = new ArrayList<File>();
//				for (int i = 0; i < listOfFiles.length; i++) 
//				{
//					if (listOfFiles[i].isFile()) 
//					{
//						configurationFile.add(listOfFiles[i]);
//						logger.info("New xml configuration file detected : "+listOfFiles[i].getName());
//					}
//				}
//            } else {
//            	logger.error("You must specify some xml configuration files");
//            	System.exit(-1);
//            }
//
//        } catch (CmdLineException e) {
//        	logger.error(e.getMessage());
//        	System.exit(-1);
//        }
//		
//	}
	
//	private boolean isAutorizedType(String type){
//		String[] packageSplited = type.split("[.]");
//		type = packageSplited[packageSplited.length-1];
//		type = Character.toLowerCase(type.charAt(0)) + type.substring(1);
//		type = type.replace("Generator", "");
//		return autorizedType.contains(type);
//	}
	
	
}
