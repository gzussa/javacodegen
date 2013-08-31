package org.gz.javacodegen.springRooJpa.fileWorkers;

import java.util.HashMap;
import java.util.Map;

import org.gz.javacodegen.fileWorkers.AbstractWriter;
import org.gz.javacodegen.springRooJpa.wrapper.DodTest;
import org.gz.javacodegen.springRooJpa.wrapper.Entity;

public class DodTestWriter extends AbstractWriter<DodTest> {
	
	static String DODTEST_TEMPLATE = "src/mods/springRooJpa/resources/templates/test/entityTestTemplates.dataOnDemand.tpl";
	private Entity entity;
	
	private static final String[] CUSTOM_CODE_TAGS = {"//CUSTOM-CODE", "//END-CUSTOM-CODE"};
	private static final String[] CUSTOM_IMPORT_TAGS = {"//CUSTOM-IMPORT", "//END-CUSTOM-IMPORT"};
	
	public DodTestWriter(DodTest wrapper, Entity entity) {
		super(wrapper);
		this.entity = entity;
	}

	@Override
	public Map<String, Object> getDataModel() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("entity", entity);
		data.put("fields", entity.getFields());
		return data;
	}

	@Override
	public String getTemplate(DodTest wrapper) {
		return DODTEST_TEMPLATE;
	}

	@Override
	public Map<String, String[]> getCodeRecoveryTags() {
		Map<String, String[]> tags = new HashMap<String, String[]>();
		tags.put("customCode", CUSTOM_CODE_TAGS);
		tags.put("customImport", CUSTOM_IMPORT_TAGS);
		return tags;
	}
	
}
