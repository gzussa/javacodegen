package org.gz.javacodegen.springRooJpa.wrapper;

import org.gz.javacodegen.fileWorkers.treeFolder.TestTreeFolderMaker;
import org.gz.javacodegen.fileWorkers.treeFolder.TreeFolderNode;
import org.gz.javacodegen.wrapper.Wrapper;

public class DodTest extends Wrapper {
	
	protected String fileName;
	protected String packageName;
	protected TreeFolderNode location;
	
	public DodTest(String fileName, String packageName){
		this.packageName = packageName;
		this.fileName = fileName; 
		this.location = TestTreeFolderMaker.getInstance().addFileToTree(packageName+"."+fileName);
	}

	@Override
	public String getFilePath() {
		return location.getIncrementalPath();
	}

}
