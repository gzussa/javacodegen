package org.gz.javacodegen.springRooJpa.writer;

import java.util.HashMap;
import java.util.Map;

import org.gz.javacodegen.fileWorkers.AbstractWriter;
import org.gz.javacodegen.springRooJpa.wrapper.Entity;
import org.gz.javacodegen.springRooJpa.wrapper.Service;

public class ServiceInterfaceWriter extends AbstractWriter<Service>{
private Entity entity; 
	
	public static final String SERVICE_TEMPLATE_INTERFACE_IMPORTS = "serviceTemplate.interface.imports.tpl";
	public static final String SERVICE_TEMPLATE_INTERFACE = "src/plugins/springRooJpa/resources/templates/service/serviceTemplate.interface.tpl";
	
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
