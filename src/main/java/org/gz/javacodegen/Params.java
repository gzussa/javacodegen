package org.gz.javacodegen;

import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

public class Params {
	@Option(name = "-h", aliases = {"--help"}, usage = "Display help")
    private boolean help;

    @Option(name = "-l", aliases = {"--list"}, usage = "folder contain a list of xml configuration files")
    private String xmlFolder;

    @Option(name = "-i", aliases = {"--input"}, usage = "xml configration path")
    private String xmlConfigFile;

    @Option(name = "-t", aliases = {"--type"}, required = true, usage = "type of project beeing generated")
    private String projectType;

    @Option(name = "-ot", aliases = {"--outputTest"}, required = true, usage = "root folder for the test sources")
    private String outputTest;
    
    @Option(name = "-om", aliases = {"--outputMain"}, required = true, usage = "root folder for the main sources")
    private String outputMain;
    
    @Argument
    private List<String> argument;
    
    public Params(){
    	help = false;
    	xmlFolder = null;
    	xmlConfigFile = null;
    	projectType = null;
    	outputTest = null;
    	outputMain = null;
    	argument = null;
    }

	public boolean isHelp() {
		return help;
	}

	public String getXmlFolder() {
		return xmlFolder;
	}

	public String getXmlConfigFile() {
		return xmlConfigFile;
	}

	public String getProjectType() {
		return projectType;
	}

	public String getOutputTest() {
		return outputTest;
	}
	
	public String getOutputMain() {
		return outputMain;
	}

	public List<String> getArgument() {
		return argument;
	}

}
