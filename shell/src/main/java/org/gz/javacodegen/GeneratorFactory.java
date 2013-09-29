package org.gz.javacodegen;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.core.AbstractGenerator;
import org.gz.javacodegen.plugin.springroojpa.SpringRooJpaGenerator;

public class GeneratorFactory {
	static Logger logger = LogManager.getLogger(GeneratorFactory.class.getName());
	
	private String plugin;
	private List<String> inputs;
	private String output;
	private boolean debug = false;
	
	/**
	 * Basic constructor, set private field based on the params object.
	 * @param params initialization object.
	 */
	public GeneratorFactory(Params params){
		this.plugin = params.getPlugin();
		this.inputs = params.getInputs();
		this.output = params.getOutput();
		this.debug = params.isDebug();
	}

	/**
	 * 
	 * TODO create a factory in order to select plugins. Use Spring IoC to get rid of any static dependencies
	 */
	public void generate(){
		for(int i = 0; i<inputs.size(); i++){
			try {
				logger.debug("call generator named : "+plugin);
				Class<?> generator = Class.forName(plugin);
				Constructor<?> constructor = generator.getConstructor(String.class, String.class);
				File inputFile = new File(inputs.get(i));
				AbstractGenerator gen = (AbstractGenerator) constructor.newInstance(output, inputFile.getAbsolutePath());
				//Execute generator
				gen.generate();
			} catch (ClassNotFoundException e) {
				logger.error("The generator associated with plugin "+plugin+" was not found");
				System.exit(-1);
			} catch (Exception e) {
				logger.error("Unexpeted error occured : " + e.getMessage());
				System.exit(-1);
			}
		}
	}
}
