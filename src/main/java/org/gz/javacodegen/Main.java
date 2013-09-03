package org.gz.javacodegen;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {

	static Logger logger = LogManager.getLogger(Main.class.getName());
	
	private List<File> mods;
	private List<String> autorizedType;
	private String projectRootTest;
	private String projectRootMain;
	private	String projectType;
	private	List<File> configurationFile;
	private Params params ;
	
	public Main(){
		mods = null;
		autorizedType = null;
		projectRootTest = null;
		projectRootMain = null;
		projectType = null;
		configurationFile = null;
		params = new Params();
	}	

	public static void main(String[] args){
		
        Main gen = new Main();
        gen.init(args);
        gen.generate();
	}
	
	private void init(String[] args){
		CmdLineParser parser = new CmdLineParser(params);
		
		// Init mods : list all mods available
		File modsFolder = new File("./src/mods");
		logger.debug("mods folder : "+modsFolder.toString());
		File[] listOfMods = modsFolder.listFiles(); 
		mods  = new ArrayList<File>();
		logger.debug(listOfMods.length+" mods found : ");
		
		autorizedType = new ArrayList<String>();
		for (int i = 0; i < listOfMods.length; i++) 
		{
			if (listOfMods[i].isDirectory()) 
			{
				mods.add(listOfMods[i]);
				logger.debug("\t"+listOfMods[i].getName());
				autorizedType.add(listOfMods[i].getName());
			}
		}
		
		
		try {
            parser.parseArgument(args);
            
            //Configure project Root
            projectRootTest = params.getOutputTest();      
            projectRootMain = params.getOutputMain();  
            
            //Configure project type
            if(isAutorizedType(params.getProjectType())){
            	projectType = params.getProjectType();
                logger.info("Project type : "+projectType);
            } else {
            	logger.error("This project type does not existe.");
            	System.exit(-1);
            }
            	
            if (params.getXmlConfigFile() != null && params.getXmlFolder() != null){
            	logger.error("You can't set both folder and file for the xml configuration files");
            	System.exit(-1);
            } else if(params.getXmlConfigFile() != null) {
            	configurationFile = new ArrayList<File>();
            	configurationFile.add(new File(params.getXmlConfigFile()));
            } else if(params.getXmlFolder() != null){
				File folder = new File(params.getXmlFolder());
				File[] listOfFiles = folder.listFiles(); 
				configurationFile = new ArrayList<File>();
				for (int i = 0; i < listOfFiles.length; i++) 
				{
					if (listOfFiles[i].isFile()) 
					{
						configurationFile.add(listOfFiles[i]);
						logger.info("New xml configuration file detected : "+listOfFiles[i].getName());
					}
				}
            } else {
            	logger.error("You must specify some xml configuration files");
            	System.exit(-1);
            }

        } catch (CmdLineException e) {
        	logger.error(e.getMessage());
        	System.exit(-1);
        }
		
	}
	
	private boolean isAutorizedType(String type){
		String[] packageSplited = type.split("[.]");
		type = packageSplited[packageSplited.length-1];
		type = Character.toLowerCase(type.charAt(0)) + type.substring(1);
		type = type.replace("Generator", "");
		return autorizedType.contains(type);
	}
		
	private void generate(){
		for(int i = 0; i<configurationFile.size(); i++){
			try {
				logger.debug("call generator named : "+projectType);
				Class<?> generator = Class.forName(projectType);
				Constructor<?> constructor = generator.getConstructor(String.class, String.class, String.class);
				AbstractGenerator gen = (AbstractGenerator) constructor.newInstance(projectRootMain, projectRootTest, configurationFile.get(i).getAbsolutePath());
				gen.generate();
			} catch (ClassNotFoundException e) {
				logger.error("The generator associat with this project type was not found");
				System.exit(-1);
			} catch (Exception e) {
				logger.error("Unexpeted error occured : " + e.getMessage());
				System.exit(-1);
			}
		}
	}
}
