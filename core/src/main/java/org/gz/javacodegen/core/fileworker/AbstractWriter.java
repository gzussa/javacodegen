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
package org.gz.javacodegen.core.fileworker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.gz.javacodegen.core.logger.LogConfigurator;
import org.gz.javacodegen.core.logger.LogHelper;
import org.gz.javacodegen.core.wrapper.Wrapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AbstractWriter<E extends Wrapper> {
	
	private static LogHelper logger = LogConfigurator.getLogger(AbstractWriter.class.getName());
	
	private E wrapper;
	
	public AbstractWriter(E wrapper){
		this.wrapper = wrapper;
	}
	
	public E getWrapper(){ return wrapper; }
	
	/**
	 * Get the template for specific wrapper
	 * @author Arnaud SAMSON
	 * @param wrapper
	 * @return path to the template
	 */
	public abstract String getTemplate(E wrapper);
	
	/**
	 * Get the hash map for freemarker with content of tags
	 * @author Arnaud SAMSON
	 * @return HashMap with name of tag and content of tag
	 */
	public abstract Map<String, Object> getDataModel();
	
	/**
	 * get the custom tags for code recovering
	 * @author Arnaud SAMSON
	 * @return Map<String, String[]> first String is the name of freemarker tag and String[] is the custom tag Ex :<"MyCode", {"//MY-CODE", "//END-MY-CODE"}>
	 */
	public abstract Map<String, String[]> getCodeRecoveryTags();
	
	/**
	 * parse and write the template on disk
	 * @author Arnaud SAMSON
	 * @param rootPath
	 */
	public void writeOnDisk(String rootPath){
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "/");
        try {
        	File outputFile = new File(rootPath+wrapper.getFilePath());
        	
        	//Checking existance of file for custom code recovering
        	Map<String, StringBuffer> customCode = null;
        	if(outputFile.exists()){
        		logger.debug("The file already exist looking for custom code");
        		customCode = this.codeRecoverer(outputFile, getCodeRecoveryTags());
        		if(customCode != null){
        			logger.debug("Custom found !!");
        			logger.debug(customCode.toString());
        		}
        	}
        	
            //Load template from source folder
        	
            Template template = cfg.getTemplate(getTemplate(wrapper));
            
            // Build the data-model
            Map<String, Object> data = getDataModel();
            if(customCode!=null){
            	Set<String> keys = customCode.keySet();
            	Iterator<String> it = keys.iterator();
            	while(it.hasNext()){
            		String key = it.next();
            		data.put(key, customCode.get(key).toString().trim());
            	}
            }
            	
            // File output
            Writer file = new FileWriter (new File(rootPath+wrapper.getFilePath()));
            template.process(data, file);
            file.flush();
            file.close();
             
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
	};
	
	/**
	 * Fill the custom code container
	 * @author Arnaud SAMSON
	 * @param file previous file if exist on disk
	 * @param tags tag to look for
	 * @return custome in an HashMap
	 */
	public Map<String, StringBuffer> codeRecoverer(File file, Map<String, String[]> tags){
		BufferedReader br = null;
		StringBuffer sb = null;
		String line = null;
		String currentTag = null;
		String excludeLine = null;
		HashMap<String, StringBuffer> retour = null;
		boolean save = false;
		try {
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				Set<String> tagsKeys = tags.keySet();
				Iterator<String> itTags = tagsKeys.iterator();
				while(itTags.hasNext()){
					String tag = itTags.next();
					String tagBegin = tags.get(tag)[0];
					String tagEnd = tags.get(tag)[1];
					if(line.contains(tagBegin) || line.contains(tagEnd)){
						if(line.contains(tagBegin)){
							logger.debug(tagBegin + " tag found");
							save = true;
							currentTag = tag;
							excludeLine = tagBegin;
							if(retour == null) retour = new HashMap<String, StringBuffer>();
							if(sb==null) sb = new StringBuffer("");
							else sb = new StringBuffer("");
						}
						else if(line.contains(tagEnd)){
							logger.debug(tagEnd+" tag found");
							retour.put(currentTag, sb);
							save = false;
						}
					}	
				}
				
				if(save){
					if(!line.contains(excludeLine)){
						sb.append(line);
						sb.append(System.getProperty("line.separator"));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return retour;
	}
}
