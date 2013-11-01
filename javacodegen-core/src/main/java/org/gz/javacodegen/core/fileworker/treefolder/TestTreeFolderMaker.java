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
package org.gz.javacodegen.core.fileworker.treefolder;

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
