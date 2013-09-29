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
	
	public static final String PLUGINS = "- springroojpa \n"
			+ "- brick";
}
