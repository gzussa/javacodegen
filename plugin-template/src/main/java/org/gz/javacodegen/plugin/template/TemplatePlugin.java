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
package org.gz.javacodegen.plugin.template;

import org.gz.javacodegen.core.AbstractPlugin;
import org.gz.javacodegen.core.logger.LogConfigurator;
import org.gz.javacodegen.core.logger.LogHelper;
/**
 * Template plugin used as demo example on how to create a new plugin
 * @author gzussa
 *
 */
public class TemplatePlugin extends AbstractPlugin {
	
	static LogHelper logger = LogConfigurator.getLogger(TemplatePlugin.class.getName());

	@Override
	public void run(String inputFile, String output) {
		logger.info("Plugin Execution:\n"
				+ "Add your plugin logic here");
		
	}

	@Override
	public void printUsage() {
		logger.info("Template Plugin:\n"
				+ "This plugin is just a template");
		
	}

	@Override
	public void printVersion() {
		logger.info("Template Plugin:\n"
				+ "Version 1");
		
	}

}