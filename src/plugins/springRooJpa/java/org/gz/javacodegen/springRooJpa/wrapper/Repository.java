package org.gz.javacodegen.springRooJpa.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

import org.gz.javacodegen.springRooJpa.wrapper.SpringItems;


@XmlRootElement(name="repository")
public class Repository extends SpringItems{

	public Repository(){
		super();
	}
	
	public Repository(String fileName, String packageName) {
		super(fileName, packageName);
	}

	public String getName(){
		return getFileName();
	}
}
