# JavaCodeGen

## About
### What is it?

**JavaCodeGen** is a **lightweigh**, **simple** and **hightly customizable** file generator framework written in Java for creating file generator tools. At this point, we are focusing on **code** generation but the framework can technically be used to generate any other sort of files. 

The framework helps developers and organizations to increase their productivity and code quality by creating code generation plugins that fit their needs. Plugins are already available and can be used out of the box if they fit your needs. Please read `README.md` files for each plugings to get more informations about their specificities. 

This framework put together all the libraries you need in order to create code generators plugins that fit your needs. 
The framework is splited in 3 parts.
* The **Core** module contains all the low level apis and libraries needed to create your plugins. It gives a simple structure on how to create plugins. For more detail about how to create plugins please read the [Plugin Template README](#template)
* The **Shell** module is the module used to catch and verify user entries when they execute the javacodegen tool. At this point the Shell module only give a specify set of actions. See the [Get Started](#getstarted) section to get more informations on how to use the JavaCodeGen tool
* The **Plugin** folder contains all the modules that are currently available. The **Template** module is a plugin bootstrap module in order to help developers to create new plugins. Please read plugins README for more informations about each plugins. 

More features will come soon. Please see the [Roadmap](#roadmap) section for more details.

### Dependencies

Feel free to add dependencies to your module if needed. Regarding the JavaCodeGen framework itself, it only has 3 dependencies:
* [Freemarker](http://freemarker.org/) used as the default and base templating engine
* [Apache log4j](http://logging.apache.org/log4j/2.x/) used as the default and base logging mechanism
* [Refletions](https://github.com/ronmamo/reflections) used as Java runtime metadata analysis (mainly use to fetch and read property files)
* [Jaxb](https://jaxb.java.net/) used as Object Xml Mapper library
* [Args4j](http://args4j.kohsuke.org/) used as Java command line arguments parser (used in the **Shell** module)

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

- Create a module in order to fetch database schema from any database and inegrate with other modules such a way that schemas can be leveraged by multiples modules. We don't want multiple modules to create this features since it can be common.
- Share ideas and enhance the Shell module so we can increase the number of options available for wider ussage of the framework.
- Integrate JSON and CSV files input. We currently support XMl input but we don't support JSON nor CSV files. Basically we need to add required library and create some utility classes.
- Add Javadoc and documentations
- Help Increase test coverage.

#### Plugins
- The SpringRooJpa plugin needs some enhancements like use JSON configuration and reverse database using enhancements cited above.
- Create a SpringRooJdo plugins. Same as SpringRooJpa except that it use the JDO standard.
- Create the Brick-Spring-Roo. Same as the SpringRooJpa and SpringRooJdo plugins with significant changes coming from entreprise best practices.
- Create the Brick-GoogleCloud. Google cloud code generator using low level datastore persistence api instead of Java standard and incorporating entreprises best practices as well.
Note: It's important to note that Brick plugins will be compatible with a coming soon lightweight Scaffolding UI called Briquette


### Pull Requests

Pull requests are welcome.  For any significant change or new feature, please start a discussion or log an issue in the [Google Group](http://groups.google.com/group/javacodegen) first.  This will save you some time, in case your idea is deemed not general enough to be included in JavaCodeGen.

Before submitting a pull request, please:

1. Write unit tests to cover any new or modified lines of code, and add it to `src/test/java`.
2. Run the Maven task to build and tests the project. See the [Build](#build) section for more info.

## Build

### Maven

JavaCodeGen uses [Maven](http://maven.apache.org) to verify each build.  If you are not familiar with Maven, check out the [getting started guide](http://maven.apache.org/guides/getting-started/index.html) for an introduction and installation instructions.

Before submitting a pull request, please run the Maven tasks.  To do so:

First, make sure you can compile without error. 

```
mvn compile
```

Then compile and run tests

```
mvn test
```

Make sur you can generate the assembled jar file

```
mvn package assembly:single
```

The assembled jar file will be in the `shell/target` folder.

Finally test your jar file

### Continuous Integration

#### Travis-CI
The Maven build is run automatically using [Travis-CI](travis-ci.org) upon every pull request and push to master.  But if any errors are found, you'll need to fix them and re-submit your pull request.  So please run the grunt task locally to save time.

## Contact

Any feedback/question/request please visit the [discussion group](http://groups.google.com/group/javacodegen)

If you are using the JavaCodeGen Framework and/or plugins, I would greatly appreciate it if you can send me a link to your project so I can get an idea on how it is being used and what kind of features/changes would be good to have in the future.

##Licence

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

