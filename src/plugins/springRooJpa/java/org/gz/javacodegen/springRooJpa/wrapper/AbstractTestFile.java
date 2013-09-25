package org.gz.javacodegen.springRooJpa.wrapper;

import org.gz.javacodegen.fileWorkers.treeFolder.TestTreeFolderMaker;
import org.gz.javacodegen.fileWorkers.treeFolder.TreeFolderNode;
import org.gz.javacodegen.wrapper.Wrapper;

public abstract class AbstractTestFile extends Wrapper {

	protected String fileName;
	protected String packageName;
	protected TreeFolderNode location;
	
	public AbstractTestFile(String fileName, String packageName){
		this.packageName = packageName;
		this.fileName = fileName; 
		this.location = TestTreeFolderMaker.getInstance().addFileToTree(packageName+"."+fileName);
	}
	
	@Override
	public String getFilePath() {
		return location.getIncrementalPath();
	}

	
}
