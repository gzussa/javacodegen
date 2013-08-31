package org.gz.javacodegen.fileWorkers.treeFolder;

import java.io.File;


/**
 * TreeFolderMaker is a singleton class for represent the project tree
 * @author Arnaud SAMSON <nono.samson@gmail.com>
 *
 */
public class TestTreeFolderMaker {

	private TreeFolderNode treeFolder;
	private static TestTreeFolderMaker instance = new TestTreeFolderMaker();

	private TestTreeFolderMaker(){
		treeFolder = null;
	}
	
	public static TestTreeFolderMaker getInstance(){
		return instance;
	}

	/**
	 * initialize the tree with the root of this tree
	 * @param root
	 */
	public void init(String root){
		treeFolder = new TreeFolderNode(root, root);
	}
	
	/**
	 * add a file to the tree folder
	 * @param packagePath (ex. path.to.my.Class) where Class is the .java file
	 */
	public TreeFolderNode addFileToTree(String path){
		String[] splitedPath = path.split("[.]");
		splitedPath[splitedPath.length-1] = splitedPath[splitedPath.length-1]+".java";
		return treeFolder.addElement(treeFolder.getIncrementalPath(), splitedPath);
	}
		
	public void addFolderToTree(String path){
		String[] splitedPath = path.split("[.]");
		treeFolder.addElement(treeFolder.getIncrementalPath(), splitedPath);
	}
	
	public String printTree(){
		return treeFolder.printPretty("", false);
	}
	
	public void writeOnDisk(){
		if(!treeFolder.isLeaf()){
			File folder = new File(treeFolder.getIncrementalPath());
			if(!folder.exists()){
				folder.mkdirs();
			}
		}
		for (int i = 0; i < treeFolder.getChilds().size(); i++){
			writeOnDisk(treeFolder.getChilds().get(i));
		}
	}
	
	private void writeOnDisk(TreeFolderNode node){
		if(!node.isLeaf()){
			File folder = new File(node.getIncrementalPath());
			if(!folder.exists()){
				folder.mkdirs();
			}
		}
		for (int i = 0; i < node.getChilds().size(); i++){
			writeOnDisk(node.getChilds().get(i));
		}
	}
}
