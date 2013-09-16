package org.gz.javacodegen.springRooJpa.writer;

import java.util.HashMap;
import java.util.Map;

import org.gz.javacodegen.fileWorkers.AbstractWriter;
import org.gz.javacodegen.springRooJpa.wrapper.Entity;
import org.gz.javacodegen.springRooJpa.wrapper.Service;

public class ServiceImplWriter extends AbstractWriter<Service> {
	private Entity entity; 
	
	public static final String SERVICE_TEMPLATE_IMPL = "src/plugins/springRooJpa/resources/templates/service/serviceTemplate.implementation.tpl";
	public static final String SERVICE_TEMPLATE_IMPL_IMPORTS = "serviceTemplate.implementation.imports.tpl";
	public static final String SERVICE_TEMPLATE_REPOSITORY_IMPL = "src/plugins/springRooJpa/resources/templates/service/serviceTemplate.implementation.repository.tpl";
	public static final String SERVICE_TEMPLATE_REPOSITORY_IMPL_IMPORTS = "serviceTemplate.implementation.repository.imports.tpl";
	
	private static final String[] CUSTOM_CODE_TAGS = {"//CUSTOM-CODE", "//END-CUSTOM-CODE"};
	private static final String[] CUSTOM_IMPORT_TAGS = {"//CUSTOM-IMPORT", "//END-CUSTOM-IMPORT"};
	
	public ServiceImplWriter(Service service, Entity entity){
		super(service);
		this.entity = entity;
	}
	
	@Override
	public String getTemplate(Service wrapper) {
		if(entity.getRepository() != null)
			return SERVICE_TEMPLATE_REPOSITORY_IMPL;
        else
        	return SERVICE_TEMPLATE_IMPL;
	}
	
	@Override
	public Map<String, Object> getDataModel() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", entity.getFileName());
        data.put("varName", entity.getVarName());
        data.put("packageName", entity.getPackageName());
        if(entity.getRepository() != null)
        	data.put("serviceImplImports", SERVICE_TEMPLATE_REPOSITORY_IMPL_IMPORTS);
        else
        	data.put("serviceImplImports", SERVICE_TEMPLATE_IMPL_IMPORTS);
        data.put("service", entity.getService());
        data.put("repository", entity.getRepository());
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
