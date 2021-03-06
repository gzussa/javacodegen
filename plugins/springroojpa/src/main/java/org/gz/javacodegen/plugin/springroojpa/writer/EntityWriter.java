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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gz.javacodegen.core.fileworker.AbstractWriter;
import org.gz.javacodegen.core.logger.LogConfigurator;
import org.gz.javacodegen.core.logger.LogHelper;
import org.gz.javacodegen.plugin.springroojpa.wrapper.Entity;
import org.gz.javacodegen.plugin.springroojpa.wrapper.Field;

public class EntityWriter extends AbstractWriter<Entity> {
	private static LogHelper logger = LogConfigurator.getLogger(EntityWriter.class.getName());
	static String ENTITY_TEMPLATE = "templates/entity/entityTemplate.tpl";
	static String ENTITY_ACTIVE_RECORD_TEMPLATE = "entityTemplate.activeRecord.tpl";
	static String ENTITY_IMPORTS_TEMPLATE = "entityTemplate.imports.tpl";
	static String ENTITY_IMPORTS_ACTIVE_RECORDE_TEMPLATE = "entityTemplate.imports.activeRecord.tpl";

	static final String[] CUSTOM_CODE_TAGS = { "//CUSTOM-CODE",
			"//END-CUSTOM-CODE" };
	static final String[] CUSTOM_IMPORT_TAGS = { "//CUSTOM-IMPORT",
			"//END-CUSTOM-IMPORT" };

	private Map<String, String> javaTypeToImports;

	public EntityWriter(Entity entity) {
		super(entity);
		javaTypeToImports = new HashMap<String, String>();
		javaTypeToImports.put("Date", "java.util.Date");
	}

	@Override
	public String getTemplate(Entity entity) {
		return ENTITY_TEMPLATE;
	}

	@Override
	public Map<String, Object> getDataModel() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", getWrapper().getFileName());
		data.put("packageName", getWrapper().getPackageName());
		data.put("serializable", getWrapper().getSerializable());
		data.put("sequenceName", getWrapper().getSequenceName());
		data.put("classicImports", ENTITY_IMPORTS_TEMPLATE);
		data.put("fieldsImport", this.getFieldsImports(getWrapper().getFields()));
		data.put("activeRecorde", getWrapper().getActiveRecord());
		data.put("activeRecordImports", ENTITY_IMPORTS_ACTIVE_RECORDE_TEMPLATE);
		data.put("table", getWrapper().getTable());
		data.put("schema", getWrapper().getSchema());
		data.put("catalogue", getWrapper().getCatalogue());
		data.put("sequenceName", getWrapper().getSequenceName());
		data.put("fields", getWrapper().getFields());
		data.put("relationships", getWrapper().getRelationships());
		data.put("activeRecordTemplate", ENTITY_ACTIVE_RECORD_TEMPLATE);
		return data;
	}

	@Override
	public Map<String, String[]> getCodeRecoveryTags() {
		Map<String, String[]> tags = new HashMap<String, String[]>();
		tags.put("customCode", CUSTOM_CODE_TAGS);
		tags.put("customImport", CUSTOM_IMPORT_TAGS);
		return tags;
	}

	public List<String> getFieldsImports(List<Field> fields){
		logger.debug("Begin custom field detection...");
		ArrayList<String> imports = new ArrayList<String>();
		for (Field field : fields) {
			if (field.getType().contains(".")) {
				logger.debug("Found some custom fields adding imports");
				imports.add(field.getType());
				int currentIndex = getWrapper().getFields().indexOf(field);
				String champs[] = field.getType().split("\\.");
				Field newField = field;
				newField.setType(champs[champs.length-1]);
				logger.debug("New field type : "+newField.getType());
				getWrapper().getFields().set(currentIndex, newField);
			} else if (javaTypeToImports.containsKey(field.getType())) {
				imports.add(javaTypeToImports.get(field.getType()));
			}
		}
		return imports;
	}

}
