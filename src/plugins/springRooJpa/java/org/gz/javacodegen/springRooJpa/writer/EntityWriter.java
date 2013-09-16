package org.gz.javacodegen.springRooJpa.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.fileWorkers.AbstractWriter;
import org.gz.javacodegen.springRooJpa.wrapper.Entity;
import org.gz.javacodegen.springRooJpa.wrapper.Field;
import org.gz.javacodegen.springRooJpa.writer.EntityWriter;

public class EntityWriter extends AbstractWriter<Entity> {	
	static String ENTITY_TEMPLATE = "src/plugins/springRooJpa/resources/templates/entity/entityTemplate.tpl";
	static String ENTITY_ACTIVE_RECORD_TEMPLATE = "entityTemplate.activeRecord.tpl";
	static String ENTITY_IMPORTS_TEMPLATE = "entityTemplate.imports.tpl";	
	static String ENTITY_IMPORTS_ACTIVE_RECORDE_TEMPLATE = "entityTemplate.imports.activeRecord.tpl";	
	static Logger logger = LogManager.getLogger(EntityWriter.class.getName());
	
	static final String[] CUSTOM_CODE_TAGS = {"//CUSTOM-CODE", "//END-CUSTOM-CODE"};
	static final String[] CUSTOM_IMPORT_TAGS = {"//CUSTOM-IMPORT", "//END-CUSTOM-IMPORT"};
	
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
        try {
			data.put("fieldsImports", getFieldsImports(getWrapper().getFields()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public List<String> getFieldsImports(List<Field> fields) throws Exception{
		ArrayList<String> imports = new ArrayList<String>();
		for(Field field : fields){
			if(field.getType().contains(".")){
				logger.debug("Found some custom fields adding imports");
				imports.add(field.getType());
			} else {
				if(javaTypeToImports.containsKey(field.getType())){
					imports.add(javaTypeToImports.get(field.getType()));
				} else {
					throw new Exception(field.getType()+" : unknow type please specify the package in the xml file");
				}
			}
		}
		return imports;
	}
	
}
