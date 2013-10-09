package org.gz.javacodegen;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.core.AbstractPlugin;
import org.gz.javacodegen.core.logger.SystemPrint;

public class PluginFactory {
	static Logger logger = LogManager.getLogger(PluginFactory.class.getName());

	private Map<String, String> pluginLocations = null;
	private Map<String, String> pluginDescriptions = null;
	
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
		this.pluginLocations = new HashMap<String, String>();
		pluginLocations.put("springroojpa", "org.gz.javacodegen.plugin.springroojpa.SpringRooJpaPlugin");
		this.pluginDescriptions = new HashMap<String, String>();
		pluginDescriptions.put("springroojpa", "Plugin that generate Spring Roo Like code.");
		//Check that the two map have the same size
		if(pluginLocations.size() != pluginDescriptions.size()){
			SystemPrint.error("Some Plugins are missing the location or description property. \n"
					+ "Please set plugin property correctly");
			System.exit(-1);
		}
	}
	
	private Map<String, String> getPluginLocations() {
		return pluginLocations;
	}
	
	private Map<String, String> getPluginDescriptions() {
		return pluginDescriptions;
	}

	/**
	 * Load plugin instance based on pluginName param passed as parameter. 
	 * @return plugin instance corresponding to pluginName
	 */
	public AbstractPlugin getPluginInstance(String pluginName){
		//First we check that the plugin exist
		Set<String> pluginNameList = pluginLocations.keySet();
		if(!pluginNameList.contains(pluginName)){
			SystemPrint.error("Plugin "+pluginName+" is not a correct plugin \n"
					+ "Please, select one of the following available plugins:");
			printPluginList();
			//If plugin name is not valid we exit with error code -1!
			System.exit(-1);
		}
		//Then we load the instance of the plugin
		AbstractPlugin plugin = null;
		String pluginLocation = pluginLocations.get(pluginName);
		try {
			Class<?> pluginClass = Class.forName(pluginLocations.get(pluginLocation));
			Constructor<?> constructor = pluginClass.getConstructor();
			plugin = (AbstractPlugin) constructor.newInstance();
		} catch (Exception e) {
			SystemPrint.error("An Exception occcured. Not able to load plugin "+pluginName+"("+pluginLocation+")");
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
		SystemPrint.info("Plugin list:\n");
		for(Iterator<String> pluginNameIterator = pluginDescriptions.keySet().iterator(); pluginNameIterator.hasNext();){
			String currentPluginName = pluginNameIterator.next();
			String currentPluginDescription = pluginDescriptions.get(currentPluginName);
			SystemPrint.info(currentPluginName+" : "+currentPluginDescription);
		}
	}
}
