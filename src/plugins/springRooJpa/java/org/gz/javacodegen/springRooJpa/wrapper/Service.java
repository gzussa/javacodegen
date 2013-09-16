package org.gz.javacodegen.springRooJpa.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

import org.gz.javacodegen.springRooJpa.wrapper.SpringItems;


@XmlRootElement(name="service")
public class Service extends SpringItems{

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
