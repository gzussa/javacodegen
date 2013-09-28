package org.gz.javacodegen.plugin.springroojpa.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

import org.gz.javacodegen.plugin.springroojpa.wrapper.AbstractSourceFile;


@XmlRootElement(name="repository")
public class Repository extends AbstractSourceFile{

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
