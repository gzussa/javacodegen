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
package org.gz.javacodegen.shell;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;

import org.gz.javacodegen.core.AbstractPlugin;
import org.gz.javacodegen.core.logger.LogConfigurator;
import org.gz.javacodegen.core.logger.LogHelper;
import org.gz.javacodegen.core.property.PluginProperties;
import org.gz.javacodegen.core.property.PropertyLoader;

public class PluginFactory {
	
	private static LogHelper logger = LogConfigurator.getLogger(PluginFactory.class.getName());

	private List<PluginProperties> pluginProperties = null;
	
	/**
	 * Basic constructor, set private field based on the params object.
	 * Check if plugin name exist and load plugin location
	 * @param parser initialization object.
	 */
	public PluginFactory(){
		//We load plugin location and plugin description
		loadPluginInfos();
	}
	
	/**
	 * TODO load using property files
	 */
	private void loadPluginInfos() {
		//Fetch plugins properties
		try {
			pluginProperties = PropertyLoader.getPluginPropertiesList();
		} catch (IOException e) {
			logger.error("Properties files not set correctly. Plese check that all you plugins property file are set correctly:\n"
					+ "Each Plugin should have at least two properties:\n"
					+ "org.gz.javacodegen.plugin.<pluginName>.location=<PluginMainClassLocation>\n"
					+ "org.gz.javacodegen.plugin.<pluginName>.description=<PluginDescription>");
			System.exit(-1);
		}
		for(Iterator<PluginProperties> pluginPropertiesIterator = pluginProperties.iterator(); pluginPropertiesIterator.hasNext();){
			//Check that PluginProperties are set correctly
			PluginProperties currentPluginProperties = pluginPropertiesIterator.next();
			if(currentPluginProperties.getLocation() == null || currentPluginProperties.getDescription() == null){
				logger.error("Plugin "+currentPluginProperties.getName()+" is not configured correctly. \n"
						+ "Please set plugin properties correctly");
				System.exit(-1);
			}
		}
	}

	/**
	 * Load plugin instance based on pluginName param passed as parameter. 
	 * @return plugin instance corresponding to pluginName
	 */
	public AbstractPlugin getPluginInstance(String pluginName){
		//First we check that the plugin exist
		PluginProperties pluginProperty = null;
		lookForPluginName : for(Iterator<PluginProperties> pluginPropertiesIterator = pluginProperties.iterator(); pluginPropertiesIterator.hasNext();){
			pluginProperty = pluginPropertiesIterator.next();		
			if(pluginProperty.getName().equals(pluginName)){
				break lookForPluginName;
			}
		}
		if(pluginProperty == null){
			logger.error("Plugin "+pluginName+" is not a correct plugin \n"
					+ "Please, select one of the following available plugins:");
			printPluginList();
			//If plugin name is not valid we exit with error code -1!
			System.exit(-1);
		}
		//Then we load the instance of the plugin
		AbstractPlugin plugin = null;
		String pluginLocation = pluginProperty.getLocation();
		try {
			Class<?> pluginClass = Class.forName(pluginLocation);
			Constructor<?> constructor = pluginClass.getConstructor();
			plugin = (AbstractPlugin) constructor.newInstance();
		} catch (Exception e) {
			logger.error("An Exception occcured. Not able to load plugin "+pluginName+"("+pluginLocation+")");
			e.printStackTrace();
			System.exit(-1);
		}
		
		return plugin;
	}

	/**
	 * Print this of available plugins
	 * Also fetch info dynamically
	 */
	public void printPluginList() {
		logger.info("Plugin list:");
		for(Iterator<PluginProperties> pluginPropertiesIterator = pluginProperties.iterator(); pluginPropertiesIterator.hasNext();){
			PluginProperties currentPluginProperties = pluginPropertiesIterator.next();
			logger.info(currentPluginProperties.getName()+" : "+currentPluginProperties.getDescription());
		}
	}
}
