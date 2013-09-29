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
package org.gz.javacodegen.core.logger;

/**
 * Helper method to print informationon Stdout and Stderr
 * @author gzussa
 *
 */
public class SystemPrint {
	
	/**
	 * Display message on Stdout
	 * @param message message to print
	 */
	public static void info(String message){
		System.out.println(message);
	}
	
	/**
	 * Display message on Stderr
	 * @param message message to print
	 */
	public static void error(String message){
		System.err.println(message);
	}
	
	/**
	 * Display message on Stdout if isDebug is set to true
	 * @param isDebug Specify if the message should be print or not
	 * @param message message to print
	 */
	public static void debug(boolean isDebug, String message){
		if(isDebug){
			System.out.println(message);
		}
	}
}
