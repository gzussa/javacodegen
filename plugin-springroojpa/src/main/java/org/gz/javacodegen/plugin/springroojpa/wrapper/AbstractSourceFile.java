package org.gz.javacodegen.plugin.springroojpa.wrapper;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.core.fileworker.treefolder.MainTreeFolderMaker;
import org.gz.javacodegen.core.fileworker.treefolder.TreeFolderNode;
import org.gz.javacodegen.plugin.springroojpa.wrapper.AbstractSourceFile;
import org.gz.javacodegen.core.wrapper.Wrapper;

public abstract class AbstractSourceFile extends Wrapper {
	protected String fileName;
	protected String varName;
	protected String packageName;
	protected TreeFolderNode location;
	static Logger logger = LogManager.getLogger(AbstractSourceFile.class.getName());
	
	public AbstractSourceFile(){
		super();
	}
	
	public AbstractSourceFile(String fileName, String packageName){
		this.fileName = fileName;
		this.packageName = packageName;
		this.location = null;
	}
	
	public String getFileName(){ return fileName; }
	public String getPackageName(){ return packageName; }
	
	@XmlAttribute(name = "packageName") public void setPackageName(String packageName){ 
		this.packageName = packageName;
		this.location = MainTreeFolderMaker.getInstance().addFileToTree(packageName+"."+fileName);
	}
	
	@XmlAttribute(name = "name") public void setFileName(String filename){ 
		this.fileName = filename; 
		this.varName = Character.toLowerCase(filename.charAt(0))+filename.substring(1);
	}
	
	public TreeFolderNode getLocation(){
		return location;
	}
	
	public String getVarName(){ return this.varName; }

	@Override
	public String getFilePath() {
		return location.getIncrementalPath();
	}
}
