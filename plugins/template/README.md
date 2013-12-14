# Template plugin

This plugin is a base canvas that will help you create a plugin very easily.

## Prerequisite

- Clone and check out the project as describe in the main [README](https://github.com/gzussa/javacodegen) file.
- Make sure that you project in imported in your favorite IDE
- Finally make sure that you are able to build and test the project using Maven.

## Get Started
Here are all the step you need to do in order to create a new plugin:
- You need to do a copy of the **template** folders and past it in the **plugins** folder (next to other plugin folders). Change the folder name so it is named like your plugin. For example, let's say I want to create a plugin called **mygenerator**, I will rename the folder **mygenerator**

- Create a package for your plugin. In this example, my plugin is called **mygenerator** so I am going to create the package `org.gz.javacodegen.plugin.mygenerator` in folder `plugins/mygenerator/src/main/java`

- Copy the **TemplatePlugin.java** class into the `org.gz.javacodegen.plugin.mygenerator` package and rename the class **MyGeneratorPlugin.java**. At this point you should have something like:
```java
 package org.gz.javacodegen.plugin.mygenerator;
 
 import org.gz.javacodegen.core.AbstractPlugin;
 import org.gz.javacodegen.core.logger.LogConfigurator;
 import org.gz.javacodegen.core.logger.LogHelper;
 /**
  * Template plugin used as demo example on how to create a new plugin
  * @author gzussa
  *
  */
 public class MyGeneratorPlugin extends AbstractPlugin { 
 	 
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
```

- You need to rename and update the **template.properties** property file located in the `plugins/mygenerator/src/main/resources` folder. Rename the file **mygenerator.properties**.
Update properties from:
```
org.gz.javacodegen.plugin.template.location=org.gz.javacodegen.plugin.template.TemplatePlugin
org.gz.javacodegen.plugin.template.description=Plugin used as creation canvas.
```
to
```
org.gz.javacodegen.plugin.mygenerator.location=org.gz.javacodegen.plugin.mygenerator.MyGeneratorPlugin
org.gz.javacodegen.plugin.mygenerator.description=My awesome generator
```

	- The first property is used by the javaCodeGen framework to locate your plugin entry point. 
	- The second property is used to print the short description displayed by the following command:
	```
	java -jar javacodegen.jar -l
	```

- Then you will need to update your pom.xml file. Go to `plugins/mygenerator/pom.xml` and change the `<artifactId>template</artifactId>` and `<name>template</name>` values to `<artifactId>mygenerator</artifactId>` and `<name>mygenerator</name>`

- Add your plugin as a module `<module>mygenerator</module>` to the `plugins/pom.xml` file.

- Add your plugin dependency to the `shell/pom.xml` file.
```xml
 <dependency>
	<groupId>org.gz.javacodegen</groupId>
	<artifactId>springroojpa</artifactId>
	<version>0.0.1</version>			
 </dependency>
```

- Your plugin is setup and you can start coding your logic and tests. Implement the 3 functions in `MyGeneratorPlugin.java`:

	- The **run(String inputFile, String output)** function will be called when you will execute the following commands:

	```
	java -jar javacodegen.jar output/ -p mygenerator inputfile.xml
	```
	and
	```
	java -jar javacodegen.jar output/ -p mygenerator inputfile1.xml inputfile1.xml
	```

	This method is called one per input file passed as parameter.

	- The **printUsage()** function will be called when you will execute the following command:
	```
	java -jar javacodegen.jar -h -p mygenerator
	```

	- The **printVersion()** function will be called when you will execute the following command:
	```
	java -jar javacodegen.jar -v -p mygenerator
	```

- Share your work!!
