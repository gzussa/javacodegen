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
package org.gz.javacodegen.plugin.springroojpa.writer;

import java.util.HashMap;
import java.util.Map;

import org.gz.javacodegen.core.fileworker.AbstractWriter;
import org.gz.javacodegen.plugin.springroojpa.wrapper.Entity;
import org.gz.javacodegen.plugin.springroojpa.wrapper.Service;

public class ServiceInterfaceWriter extends AbstractWriter<Service>{
private Entity entity; 
	
	public static final String SERVICE_TEMPLATE_INTERFACE_IMPORTS = "serviceTemplate.interface.imports.tpl";
	public static final String SERVICE_TEMPLATE_INTERFACE = "templates/service/serviceTemplate.interface.tpl";
	
	private static final String[] CUSTOM_CODE_TAGS = {"//CUSTOM-CODE", "//END-CUSTOM-CODE"};
	private static final String[] CUSTOM_IMPORT_TAGS = {"//CUSTOM-IMPORT", "//END-CUSTOM-IMPORT"};
	
	public ServiceInterfaceWriter(Service service, Entity entity){
		super(service);
		this.entity = entity;
	}

	@Override
	public String getTemplate(Service wrapper) {
		return SERVICE_TEMPLATE_INTERFACE;
	}

	@Override
	public Map<String, Object> getDataModel() {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", entity.getFileName());
        data.put("varName", entity.getVarName());
        data.put("packageName", entity.getPackageName());
        data.put("serviceInterfaceImports", SERVICE_TEMPLATE_INTERFACE_IMPORTS);
        data.put("service", entity.getService());
		return data;
	}

	@Override
	public Map<String, String[]> getCodeRecoveryTags() {
		Map<String, String[]> tags = new HashMap<String, String[]>();
		tags.put("customCode", CUSTOM_CODE_TAGS);
		tags.put("customImport", CUSTOM_IMPORT_TAGS);
		return tags;
	}
}
