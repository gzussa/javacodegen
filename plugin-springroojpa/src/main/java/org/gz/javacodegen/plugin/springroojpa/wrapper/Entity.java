/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Gregory
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package org.gz.javacodegen.plugin.springroojpa.wrapper;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.gz.javacodegen.plugin.springroojpa.wrapper.Field;
import org.gz.javacodegen.plugin.springroojpa.wrapper.Repository;
import org.gz.javacodegen.plugin.springroojpa.wrapper.Service;
import org.gz.javacodegen.plugin.springroojpa.wrapper.AbstractSourceFile;


@XmlRootElement(name="entity")
public class Entity extends AbstractSourceFile{
	
	private String name;
	private String persistenceType;
	private Boolean serializable;
	private Boolean activeRecorde;
	private String sequenceName;
	private String catalogue;
	private String schema;
	private String table;
	private ArrayList<Field> fields;
	private ArrayList<Relationship> relationships;
	private Service service;
	private Repository repository;
	
	public Entity(){
		super();
	}
	
	public Entity(String fileName, String packageName) {
		super(fileName, packageName);
		this.name = fileName;
	}
	
	@XmlAttribute(name="entity:persistenceType") public void setPersistenceType(String persistenceType){ this.persistenceType = persistenceType; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/entity") public void setSerializable(Boolean serializable){ this.serializable = serializable; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/entity") public void setActiveRecorde(Boolean activeRecorde){ this.activeRecorde = activeRecorde; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/entity") public void setSequenceName(String sequenceName){ this.sequenceName = sequenceName;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/entity") public void setCatalogue(String catalogue){ this.catalogue = catalogue;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/entity") public void setSchema(String schema){ this.schema = schema; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/entity") public void setTable(String table){ this.table = table; }
	@XmlElement(name="service") public void setService(Service service){ this.service = service; }
	@XmlElement(name="repository") public void setRepository(Repository repository){ this.repository = repository; }
	@XmlElementWrapper(name="fields")
	@XmlElement(name = "field") 
	public void setFields(ArrayList<Field> fields){ this.fields = fields; }
	@XmlElementWrapper(name="relationships")
	@XmlElement(name = "relationship") 
	public void setRelationships(ArrayList<Relationship> relationships){ this.relationships = relationships; }
	

	public String getPersistenceType(){ return this.persistenceType; }
	public Boolean getSerializable(){ return this.serializable; }
	public Boolean getActiveRecord(){ return this.activeRecorde; }
	public String getSequenceName(){ return this.sequenceName; }
	public String getCatalogue(){ return this.catalogue; }
	public String getSchema(){ return this.schema; }
	public String getTable(){ return this.table; }
	public Repository getRepository(){ return this.repository; }
	public Service getService(){ return this.service; }
	public ArrayList<Field> getFields(){ return this.fields; }
	public ArrayList<Relationship> getRelationships(){ return this.relationships; }
	public String getName(){ return name; }

	@Override
	public String toString() {
		return "Entity [name="+fileName+", packageName="+packageName+", persistenceType=" + persistenceType + ", serializable="
				+ serializable + ", activeRecorde=" + activeRecorde
				+ ", sequenceName=" + sequenceName + ", catalogue=" + catalogue
				+ ", schema=" + schema + ", table=" + table + ", service="
				+ service + ", repository=" + repository + "]";
	}

	
}
