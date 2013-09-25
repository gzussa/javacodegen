package org.gz.javacodegen.springRooJpa.writer;

import java.util.HashMap;
import java.util.Map;

import org.gz.javacodegen.fileWorkers.AbstractWriter;
import org.gz.javacodegen.springRooJpa.wrapper.Entity;
import org.gz.javacodegen.springRooJpa.wrapper.IntegrationTest;

public class IntegrationTestWriter extends AbstractWriter<IntegrationTest>{

	static String INTEGRATIONTEST_TEMPLATE = "src/plugins/springRooJpa/resources/templates/test/entityTestTemplates.integrationTest.tpl";
	private Entity entity;
	
	private static final String[] CUSTOM_CODE_TAGS = {"//CUSTOM-CODE", "//END-CUSTOM-CODE"};
	private static final String[] CUSTOM_IMPORT_TAGS = {"//CUSTOM-IMPORT", "//END-CUSTOM-IMPORT"};
	
	public IntegrationTestWriter(IntegrationTest wrapper, Entity entity) {
		super(wrapper);
		this.entity = entity;
	}

	@Override
	public Map<String, Object> getDataModel() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("entity", entity);
		return data;
	}

	@Override
	public String getTemplate(IntegrationTest wrapper) {
		return INTEGRATIONTEST_TEMPLATE;
	}

	@Override
	public Map<String, String[]> getCodeRecoveryTags() {
		Map<String, String[]> tags = new HashMap<String, String[]>();
		tags.put("customCode", CUSTOM_CODE_TAGS);
		tags.put("customImport", CUSTOM_IMPORT_TAGS);
		return tags;
	}

}
