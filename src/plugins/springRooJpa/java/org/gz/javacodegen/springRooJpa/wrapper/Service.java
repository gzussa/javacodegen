package org.gz.javacodegen.springRooJpa.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

import org.gz.javacodegen.springRooJpa.wrapper.AbstractSourceFile;


@XmlRootElement(name="service")
public class Service extends AbstractSourceFile{

	public Service(){
		super();
	}
	
	public Service(String fileName, String packageName) {
		super(fileName, packageName);
	}

	public String getName(){
		return getFileName();
	}
}
