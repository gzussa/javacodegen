package org.gz.javacodegen.springRooJpa.wrapper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="relationship")
public class Relationship {
	String name;
	String upperName;
	String type;
	Boolean notNull;
	String mappedBy;
	String cardinality;
	String fetch;
	String joinColumnName;
	String referencedColumnName;
	
	public Relationship(){
		name = "";
		upperName = "";
		type = "";
		notNull = false;
		mappedBy = "";
		cardinality = "";
		fetch = "";
		joinColumnName = "";
		referencedColumnName = "";
	}
	
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setName(String name) { 
		this.name = name;
		this.upperName = Character.toUpperCase(name.charAt(0))+name.substring(1);
	}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setType(String type) { this.type = type; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setNotNull(Boolean notNull) { this.notNull = notNull; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setMappedBy(String mappedBy) { this.mappedBy = mappedBy; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setCardinality(String cardinality) { this.cardinality = cardinality; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setFetch(String fetch) { this.fetch = fetch; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setJoinColumnName(String joinColumnName) { this.joinColumnName = joinColumnName; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field")public void setReferencedColumnName(String referencedColumnName) { this.referencedColumnName = referencedColumnName; }	

	public String getName() { return name; }
	public String getUpperName() { return upperName; } 
	public String getType() { return type; }	
	public Boolean getNotNull() { return notNull; }
	public String getMappedBy() { return mappedBy; }
	public String getCardinality() { return cardinality; }	
	public String getFetch() { return fetch; }
	public String getJoinColumnName() { return joinColumnName; }
	public String getReferencedColumnName() { return referencedColumnName; }
}
