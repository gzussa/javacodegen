package org.gz.javacodegen.springRooJpa.wrapper;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.fileWorkers.treeFolder.MainTreeFolderMaker;
import org.gz.javacodegen.fileWorkers.treeFolder.TreeFolderNode;
import org.gz.javacodegen.springRooJpa.wrapper.SpringItems;
import org.gz.javacodegen.wrapper.Wrapper;

public class SpringItems extends Wrapper {
	protected String fileName;
	protected String varName;
	protected String packageName;
	protected TreeFolderNode location;
	static Logger logger = LogManager.getLogger(SpringItems.class.getName());
	
	public SpringItems(){
		super();
	}
	
	public SpringItems(String fileName, String packageName){
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
