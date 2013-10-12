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
package org.gz.javacodegen.plugin.springroojpa.wrapper;

import javax.xml.bind.annotation.XmlAttribute;

import org.gz.javacodegen.core.fileworker.treefolder.MainTreeFolderMaker;
import org.gz.javacodegen.core.fileworker.treefolder.TreeFolderNode;
import org.gz.javacodegen.plugin.springroojpa.wrapper.AbstractSourceFile;
import org.gz.javacodegen.core.wrapper.Wrapper;

public abstract class AbstractSourceFile implements Wrapper {
	
	protected String fileName;
	protected String varName;
	protected String packageName;
	protected TreeFolderNode location;
	
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

	public String getFilePath() {
		return location.getIncrementalPath();
	}
}
