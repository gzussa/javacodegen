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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="field")
public class Field {
	
	private String name;
	private String upperName;
	private String type;
	private String persistenceType;
	private Boolean notNull;
	private String defaultValue;
	private Boolean isTransient;
	private Boolean unique;
	private Boolean future;
	private Boolean past;
	private String dateTimeFormatPattern;
	private String contentType;
	private String decimalMin;
	private String decimalMax;
	private String digitInteger;
	private String digitFraction;
	private String min;
	private String max;
	private String sizeMin;
	private String sizeMax;
	private String regexp;
	private String temporalType;
	
	public Field(){
		name = "";
		upperName = "";
		type = "";
		persistenceType = "";
		notNull = false;
		defaultValue = "";
		isTransient = false;
		unique = false;
		future = false;
		past = false;
		dateTimeFormatPattern = "";
		contentType = "";
		decimalMin = "";
		decimalMax = "";
		digitInteger = "";
		digitFraction = "";
		min = "";
		max = "";
		sizeMin = "";
		sizeMax = "";
		regexp = "";
		temporalType = "";
	}
	
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setName(String name){ 
		this.name = name; 
		this.upperName = Character.toUpperCase(name.charAt(0))+name.substring(1);
	}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setType(String type){ this.type = type; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setPersistenceType(String persistenceType){ this.persistenceType = persistenceType; }
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setNotNull(Boolean notNull){this.notNull = notNull;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setDefaultValue(String defaultValue){this.defaultValue = defaultValue;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setIsTransient(Boolean isTransient){this.isTransient = isTransient;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setUnique(Boolean unique){this.unique = unique;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setFuture(Boolean future){this.future = future;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setPast(Boolean past){this.past = past;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setDateTimeFormatPattern(String dateTimeFormatPattern){this.dateTimeFormatPattern = dateTimeFormatPattern;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setContentType(String contentType){this.contentType = contentType;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setDecimalMin(String decimalMin){this.decimalMin = decimalMin;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setDecimalMax(String decimalMax){this.decimalMax = decimalMax;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setDigitInteger(String digitInteger){this.digitInteger = digitInteger;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setDigitFraction(String digitFraction){this.digitFraction = digitFraction;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setMin(String min){this.min = min;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setMax(String max){this.max = max;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setSizeMin(String sizeMin){this.sizeMin = sizeMin;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setSizeMax(String sizeMax){this.sizeMax = sizeMax;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setRegexp(String regexp){this.regexp = regexp;}
	@XmlAttribute(namespace="http://www.myproject.com/xml/field") public void setTemporalType(String temporalType){this.temporalType = temporalType;}
	
	public String getName(){ return name; }
	public String getType(){ return type; }
	public String getUpperName(){ return upperName;}
	public String getPersistenceType(){ return persistenceType;}
	public Boolean getNotNull(){return notNull;}
	public String getDefaultValue(){return defaultValue;}
	public Boolean getIsTransient(){return isTransient;}
	public Boolean getUnique(){return unique;}
	public Boolean getFuture(){return future;}
	public Boolean getPast(){return past;}
	public String getDateTimeFormatPattern(){return dateTimeFormatPattern;}
	public String getContentType(){return contentType;}
	public String getDecimalMin(){return decimalMin;}
	public String getDecimalMax(){return decimalMax;}
	public String getDigitInteger(){return digitInteger;}
	public String getDigitFraction(){return digitFraction;}
	public String getMin(){return min;}
	public String getMax(){return max;}
	public String getSizeMin(){return sizeMin;}
	public String getSizeMax(){return sizeMax;}
	public String getRegexp(){return regexp;}
	public String getTemporalType(){return temporalType;}
		
	@Override
	public String toString() {
		return "Field [name=" + name + ", type=" + type + "]";
	}
}
