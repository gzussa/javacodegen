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
package org.gz.javacodegen;
/**
 * Interface containing basic and global constants
 * @author gzussa
 *
 */
public interface Constants {
	
	public static final String USAGE_EXAMPLES = "EXAMPLES:\n"
			+ "java -jar javacodegen.jar -h : Display Javacodegen help \n"
			+ "java -jar javacodegen.jar -h -p pluginname : Display plugin 'foo' help \n"
			+ "java -jar javacodegen.jar -l : Display list of available plugins \n"
			+ "java -jar javacodegen.jar -v : Display Javacodegen version \n"
			+ "java -jar javacodegen.jar -v -p pluginname : Display plugin 'foo' version \n"
			+ "java -jar javacodegen.jar outputfolder/ -p foo inputfile.xml : Run plugin 'foo' using inputfile.xml. outputfolder/ correspond to where the plugin is going to generate files \n"
			+ "java -jar javacodegen.jar outputfolder/ -p foo inputfile1.xml inputfile2.xml ... : Same as above but with multiple input files\n";
	
	public static final String VERSION = "JAVACODEGEN Version 0.0.1";
	
	public static final String OUTPUT = "OPTIONS:";
	
	public static final String PLUGINS = "- springroojpa \n"
			+ "- brick";
}
