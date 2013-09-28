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
package org.gz.javacodegen.core;

import org.gz.javacodegen.core.fileworker.treefolder.MainTreeFolderMaker;
import org.gz.javacodegen.core.fileworker.treefolder.TestTreeFolderMaker;

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
