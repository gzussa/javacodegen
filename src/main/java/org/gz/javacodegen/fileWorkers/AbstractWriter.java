package org.gz.javacodegen.fileWorkers;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.wrapper.Wrapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AbstractWriter<E extends Wrapper> {
	static Logger logger = LogManager.getLogger(AbstractWriter.class.getName());
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
