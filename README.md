# JavaCodeGen

## About
### What is it?

**JavaCodeGen** is a **lightweight**, **easy** to use and **highly customizable** file generator framework written in Java for creating file generator tools. At this point, we are focusing on **code** generation tool but the framework can technically be used to generate any sort of files. 

The framework helps developers and organizations to increase their productivity and code quality by creating code generation tools that fit their needs. Generators are defined through plugins. Basically, a plugin represents one generator. A generator is the combination of the JavaCodeGen framework plus one specific plugins. Plugins are already available and can be used out of the box if they fit your needs. Please read `README` files for each plugins to get more informations about their specificities. 

This framework put together libraries, the structure and low level apis you need in order to create code generators plugins that fit your needs. 
The framework is split in 3 parts.
* The **Core** module contains all the low level apis needed to create and run a plugin. It gives you a simple structure that makes plugin creation very easy to do. For more detail about how to create plugins please read the [Template Plugin README](https://github.com/gzussa/javacodegen/tree/master/plugins/template) file.
* The **Shell** module is the module used to catch and verify user entries when they execute the JavaCodeGen tool. At this point, the Shell module only gives a specific set of actions. See the [Get Started](#get-started) section to get more informations on how to use the JavaCodeGen tool
* The **Plugin** folder contains all the plugins that are currently available. The **Template** plugin can be used as a bootstrap or get starting bundle for developers who want to create new plugins. Please read plugins READMEs for more informations about each plugins. 

More features will come soon. Please see the [Roadmap](#roadmap) section for more details.

### Dependencies

Feel free to add dependencies to your module/plugin if needed. Regarding the JavaCodeGen framework itself, it only has 5 dependencies:
* [Freemarker](http://freemarker.org/) used as the default template engine
* [Apache log4j](http://logging.apache.org/log4j/2.x/) used as the default and base logging mechanism
* [Reflections](https://github.com/ronmamo/reflections) used as Java runtime metadata analysis (mainly use to fetch and read property files)
* [Jaxb](https://jaxb.java.net/) used as Object Xml Mapper library
* [Args4j](http://args4j.kohsuke.org/) used as Java command line arguments parser (used in the **shell** module)

## Get Started

### How to Get JavaCodeGen?

#### Build From Source

Clone and Check out the sources from https://github.com/gzussa/javacodegen.git and then import the project into Eclipse as a Java Project. Then set up your project as a Maven project so you can fix dependencies.

Make sure Maven is installed and run the following maven command to package and assemble your source code:

```
mvn package assembly:single
```
For more details on how to build and test the project, please check the [Build](#build) section.

#### Run JavaCodeGen

The assembled jar file will be in the `shell/target` folder.

Finally you can run the jar by doing:

```
java -jar shell-0.0.1-jar-with-dependencies.jar
```
Note that the above jar name may not be up-to-date.

#### Usages
By running the last example, we should see an error message and usage examples in your console. The error message appears because we haven't passed any parameter and options to JavaCodeGen

```
> java -jar javacodegen.jar

\[ERROR\]: OPTIONS:
 VAL               : Base output
 VAL               : Input file(s)
 -h (--help)       : Display tool or plugin help
 -l (--list)       : Display list of available plugins
 -p (--plugin) VAL : Plugin to use
 -v (--version)    : Display tool or plugin Version
 -x (--debug)      : Run Command in debug mode. This option can be used in every
                      usage possible
\[ERROR\]: EXAMPLES:
java -jar javacodegen.jar -h : Display Javacodegen help
java -jar javacodegen.jar -h -p pluginname : Display plugin 'foo' help
java -jar javacodegen.jar -l : Display list of available plugins
java -jar javacodegen.jar -v : Display Javacodegen version
java -jar javacodegen.jar -v -p pluginname : Display plugin 'foo' version
java -jar javacodegen.jar outputfolder/ -p foo inputfile.xml : Run plugin 'foo' using inputfile.xml. outputfolder/ correspond to where the plugin is going to generate files
java -jar javacodegen.jar outputfolder/ -p foo inputfile1.xml inputfile2.xml ... : Same as above but with multiple input files
```
Note that the above example may not be up-to-date. Also, we renamed the packaged jar into `javacodegen.jar`

##### Debug
The `-x` option can be used every time you want. If will display some debug informations that sometime can be very useful.

##### Plugin list
At this point in time, we only have two plugins available, the **template** plugin and the **springroojpa** plugin.
By executing the following command you will get the list of available plugins:
```
> java -jar javacodegen.jar -l

Plugin list:
template : Plugin used as creation canvas.
springroojpa : Plugin that generate Spring Roo Like code.
```
##### Versions

By execution the following command you will get the version of the JavaCodeGen framework

```
> java -jar javacodegen.jar -v

AVACODEGEN Version 0.0.1
```

You can also get the version of a specific plugin (as named in the plugin list). For example, If we want to know the version of the **template** plugin, we just need to do the following:

```
> java -jar javacodegen.jar -v -p template

Template Plugin:
Version 1
```

##### Help
You can also ask for help with the following command (output is currently the same as when we don't use any argument or option):

```
> java -jar javacodegen.jar -h

OPTIONS:
 VAL               : Base output
 VAL               : Input file(s)
 -h (--help)       : Display tool or plugin help
 -l (--list)       : Display list of available plugins
 -p (--plugin) VAL : Plugin to use
 -v (--version)    : Display tool or plugin Version
 -x (--debug)      : Run Command in debug mode. This option can be used in every
                      usage possible
EXAMPLES:
java -jar javacodegen.jar -h : Display Javacodegen help
java -jar javacodegen.jar -h -p pluginname : Display plugin 'foo' help
java -jar javacodegen.jar -l : Display list of available plugins
java -jar javacodegen.jar -v : Display Javacodegen version
java -jar javacodegen.jar -v -p pluginname : Display plugin 'foo' version
java -jar javacodegen.jar outputfolder/ -p foo inputfile.xml : Run plugin 'foo' using inputfile.xml. outputfolder/ correspond to where the plugin is going to generate files
java -jar javacodegen.jar outputfolder/ -p foo inputfile1.xml inputfile2.xml ... : Same as above but with multiple input files
```
We can also get plugin help only in order to access plugin documentation. See example below with the **template** plugin:

```
> java -jar javacodegen.jar -h -p template

Template Plugin:
This plugin is just a template
```
Note that template help is very small since this plugin doesn't do anything particular.

##### Plugin execution

As of now, they are only two ways to interact with a plugin. More options will come in the future. 
```
java -jar javacodegen.jar outputfolder/ -p foo inputfile.xml
```

and 

```
java -jar javacodegen.jar outputfolder/ -p foo inputfile1.xml inputfile2.xml ...
```

- The `outputfolder/` represents the folder where generated files will be written on your disk.
- The `foo` parameter represent the name of the plugin you want to use.
- `xml` input files are input files for your plugins. Input file configuration is specific to each plugin. Please check plugin documentation to get more details on how to create input files.

## Get Involved
### Ways to Contribute

Has JavaCodeGen been helpful to you?  If you'd like to give back, here are a few ways:

1. Blog about your experiences using JavaCodeGen, and let us know about it!
2. Create and Share plugins you create or support!
3. Create/Find use cases using JavaCodeGen and add it to the documentation.
4. Improve the docs in the README.
5. Fix a bug or add a new feature and submit a pull request (see below).

### Roadmap
Here is a list of things that would need to be done regarding the framework and high level plugins ideas:

#### Framework

- Create a module in order to fetch database schema from any database and integrate with other plugins such a way that schemas can be leveraged by multiple plugins. We don't want multiple plugins to create this features since it can be common.
- Share ideas and enhance the **shell** module so we can increase the number of options available for wider usage of the framework.
- Integrate JSON and CSV files input. We currently support XML inputs but we don't support JSON nor CSV files. Basically we need to add required library and create some utility classes.
- Add Javadoc and documentations.
- Help increase test coverage.

#### Plugins
- The SpringRooJpa plugin needs some enhancements like use JSON configuration and reverse database using enhancements cited above.
- Create a SpringRooJdo plugins. Same as SpringRooJpa except that it use the JDO standard.
- Create the Brick-Spring-Roo. Same as the SpringRooJpa and SpringRooJdo plugins with significant changes coming from enterprise best practices.
- Create the Brick-GoogleCloud. Google cloud code generator using low level datastore persistence api instead of Java standard and incorporating enterprises best practices as well.
Note: It's important to note that Brick plugins will be compatible with a coming soon lightweight Scaffolding UI called Briquette


### Pull Requests

Pull requests are welcome. For any significant change or new feature, please start a discussion or log an issue in the [Google Group](http://groups.google.com/group/javacodegen) first.  This will save you some time, in case your idea is deemed not general enough to be included in JavaCodeGen.

Before submitting a pull request, please:

1. Write unit tests to cover any new or modified lines of code, and add it to `src/test/java`.
2. Run the Maven task to build and tests the project. See the [Build](#build) section for more info.

## Build

### Maven

JavaCodeGen uses [Maven](http://maven.apache.org) to verify each build. If you are not familiar with Maven, check out the [getting started guide](http://maven.apache.org/guides/getting-started/index.html) for an introduction and installation instructions.

Before submitting a pull request, please run Maven tasks to make sure the project compile and your changes didn't introduce regressions. To do so:

First, make sure you can compile without error. 

```
mvn compile
```

Then compile and run tests

```
mvn test
```

Make sure you can generate the assembled jar file

```
mvn package assembly:single
```

The assembled jar file will be in the `shell/target` folder.

Finally test your jar file

### Build for specific plugins only

You don't need to package the entire source code with every plugins if you are only using few of them.
You can remove any plugins you want. Just remove plugin folders you don't need. Also, you will need to fix Maven dependencies.
Remove corresponding dependencies in the `shell/pom.xml` file and module definitions in the `plugins/pom.xml` file.

### Continuous Integration

[![Build Status](https://travis-ci.org/gzussa/javacodegen.png?branch=master)](https://travis-ci.org/gzussa/javacodegen)

#### Travis-CI
The Maven build is run automatically using [Travis-CI](travis-ci.org) upon every pull request and push to master.  But if any errors are found, you'll need to fix them and re-submit your pull request.  So please run the grunt task locally to save time.

## Contact

Any feedback/question/request please visit the [discussion group](http://groups.google.com/group/javacodegen)

If you are using the JavaCodeGen Framework and/or plugins, I would greatly appreciate it if you can send me a link to your project so I can get an idea on how it is being used and what kind of features/changes would be good to have in the future.

##License

The MIT License (MIT)

Copyright (c) 2013 Gregory

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

