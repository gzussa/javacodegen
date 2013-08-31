package org.gz.javacodegen.springRooJpa.fileWorkers;

import java.util.HashMap;
import java.util.Map;

import org.gz.javacodegen.fileWorkers.AbstractWriter;
import org.gz.javacodegen.springRooJpa.wrapper.Entity;
import org.gz.javacodegen.springRooJpa.wrapper.Repository;

public class RepositoryWriter extends AbstractWriter<Repository>{
	private Entity entity; 
	
	public static final String REPOSITORY_TEMPLATE_IMPORTS = "repositoryTemplate.interface.imports.tpl";
	public static final String REPOSITORY_TEMPLATE = "src/mods/springRooJpa/resources/templates/repository/repositoryTemplate.interface.tpl";
	
	private static final String[] CUSTOM_CODE_TAGS = {"//CUSTOM-CODE", "//END-CUSTOM-CODE"};
	private static final String[] CUSTOM_IMPORT_TAGS = {"//CUSTOM-IMPORT", "//END-CUSTOM-IMPORT"};
	
	public RepositoryWriter(Repository repository, Entity entity){
		super(repository);
		this.entity = entity;
	}

	@Override
	public String getTemplate(Repository wrapper) {
		return REPOSITORY_TEMPLATE;
	}

	@Override
	public Map<String, Object> getDataModel() {
		Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", entity.getFileName());
        data.put("packageName", entity.getPackageName());
        data.put("repositoryImports", REPOSITORY_TEMPLATE_IMPORTS);
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
