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
package org.gz.javacodegen.core.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.common.base.Predicate;
/**
 * Util class that deal with property files
 * @author gzussa
 *
 */
public class PropertyLoader {

	/**
	 * load every properties contained in the classpath
	 * @return properties Object containing all loaded properties from every properties files contained in the classpath
	 * @throws IOException
	 */
	public static Properties getProperties() throws IOException{
		Predicate<String> filter = new FilterBuilder().include(".*.properties");
		
	    Reflections reflections = new Reflections(new ConfigurationBuilder()
	            .filterInputsBy(filter)
	            .setScanners(new ResourcesScanner())
	            .setUrls(Arrays.asList(ClasspathHelper.forClass(PropertyLoader.class))));
	    Set<String> propertiesfiles = reflections.getStore().get(ResourcesScanner.class).keySet();
	    Properties prop = new Properties();
	    for(Iterator<String> propertiesFilesIterator =  propertiesfiles.iterator(); propertiesFilesIterator.hasNext();){
	    	String currentPropertyFile = propertiesFilesIterator.next();
	    	InputStream is = PropertyLoader.class.getClassLoader().getResourceAsStream(currentPropertyFile);
	    	if(is != null){
	    		prop.load(is);
	    	}
	    }
	    return prop;
	}
	
	/**
	 * Create a list of PluginProperties based on property file configurations
	 * @return List of PluginProperties
	 * @throws IOException
	 */
	public static List<PluginProperties> getPluginPropertiesList() throws IOException{
		List<PluginProperties> result = new ArrayList<PluginProperties>();
		List<String> pluginNameList = new ArrayList<String>();
		//Fetch all properties
		Properties properties = getProperties();
		//Loop over every property in order to fill result variable
		for(Iterator<Object> propertiesKeyIterator = properties.keySet().iterator(); propertiesKeyIterator.hasNext();){
			String currentPropertyKey = (String)propertiesKeyIterator.next();
			if(currentPropertyKey.startsWith(PropertyConstants.PLUGIN_PREFIX_PROPERTY)){
				String pluginName = currentPropertyKey.replace(PropertyConstants.PLUGIN_PREFIX_PROPERTY, "").replace(PropertyConstants.PLUGIN_SUFFIX_LOCATION_PROPERTY, "").replace(PropertyConstants.PLUGIN_SUFFIX_DESCRIPTION_PROPERTY, "");

				if(!pluginNameList.contains(pluginName)){					
					String pluginLocation = properties.getProperty(PropertyConstants.PLUGIN_PREFIX_PROPERTY+pluginName+PropertyConstants.PLUGIN_SUFFIX_LOCATION_PROPERTY);
					String pluginDescription = properties.getProperty(PropertyConstants.PLUGIN_PREFIX_PROPERTY+pluginName+PropertyConstants.PLUGIN_SUFFIX_DESCRIPTION_PROPERTY);
					PluginProperties pluginProperties = new PluginProperties(pluginName, pluginLocation, pluginDescription);
					result.add(pluginProperties);
					pluginNameList.add(pluginName);
				}
			}
		}
		return result;
	}
}
