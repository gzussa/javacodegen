package org.gz.javacodegen;

import org.gz.javacodegen.fileWorkers.treeFolder.MainTreeFolderMaker;
import org.gz.javacodegen.fileWorkers.treeFolder.TestTreeFolderMaker;

public abstract class AbstractGenerator {
	private String srcRootPath;
	private String testRootPath;
	private String xmlConfigFile;
	
	public AbstractGenerator(String srcRootPath, String testRootPath, String xmlConfigFile){
		this.srcRootPath = srcRootPath;
		this.testRootPath = testRootPath;
		this.xmlConfigFile = xmlConfigFile;
		
		MainTreeFolderMaker.getInstance().init(srcRootPath);
		TestTreeFolderMaker.getInstance().init(testRootPath);
	}
	
	public abstract void generate();
	
	public String getXmlConfigFile(){
		return xmlConfigFile;
	}
	
	public String getSrcRootPath(){
		return srcRootPath;
	}
	
	public String getTestRootPath(){
		return testRootPath;
	}
}
