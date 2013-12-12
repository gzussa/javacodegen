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
import org.gz.javacodegen.plugin.springroojpa.wrapper.IntegrationTest;

public class IntegrationTestWriter extends AbstractWriter<IntegrationTest>{

	static String INTEGRATIONTEST_TEMPLATE = "templates/test/entityTestTemplates.integrationTest.tpl";
	
	static String[] COUNT_TEMPLATE = {
		"entityTestTemplates.integrationTest.count.tpl",
		"entityTestTemplates.integrationTest.repositoryCount.tpl",
		"entityTestTemplates.integrationTest.serviceCount.tpl"
	};
	static String[] FIND_TEMPLATE = {
		"entityTestTemplates.integrationTest.find.tpl",
		"entityTestTemplates.integrationTest.repositoryFind.tpl",
		"entityTestTemplates.integrationTest.serviceFind.tpl"
	};
	static String[] FINDALL_TEMPLATE = {
		"entityTestTemplates.integrationTest.findAll.tpl",
		"entityTestTemplates.integrationTest.repositoryFindAll.tpl",
		"entityTestTemplates.integrationTest.serviceFindAll.tpl"
	};
	static String[] FINDENTITYENTRIES_TEMPLATE = {
		"entityTestTemplates.integrationTest.findEntityEntries.tpl",
		"entityTestTemplates.integrationTest.repositoryFindEntityEntries.tpl",
		"entityTestTemplates.integrationTest.serviceFindEntityEntries.tpl"
	};
	static String[] FLUSH_TEMPLATE = {
		"entityTestTemplates.integrationTest.flush.tpl",
		"entityTestTemplates.integrationTest.repositoryFlush.tpl",
		"entityTestTemplates.integrationTest.serviceFlush.tpl"
	};
	static String[] MERGEUPDATE_TEMPLATE = {
		"entityTestTemplates.integrationTest.mergeUpdate.tpl",
		"entityTestTemplates.integrationTest.repositoryMergeUpdate.tpl",
		"entityTestTemplates.integrationTest.serviceMergeUpdate.tpl"
	};
	static String[] PERSIST_TEMPLATE = {
		"entityTestTemplates.integrationTest.persist.tpl",
		"entityTestTemplates.integrationTest.repositoryPersist.tpl",
		"entityTestTemplates.integrationTest.servicePersist.tpl"
	};
	static String[] REMOVE_TEMPLATE = {
		"entityTestTemplates.integrationTest.remove.tpl",
		"entityTestTemplates.integrationTest.repositoryRemove.tpl",
		"entityTestTemplates.integrationTest.serviceRemove.tpl"
	};
		
	private static final String[] CUSTOM_CODE_TAGS = {"//CUSTOM-CODE", "//END-CUSTOM-CODE"};
	private static final String[] CUSTOM_IMPORT_TAGS = {"//CUSTOM-IMPORT", "//END-CUSTOM-IMPORT"};
	
	private Entity entity;
	private int templateType;
	
	public IntegrationTestWriter(IntegrationTest wrapper, Entity entity) {
		super(wrapper);
		this.entity = entity;
		if(entity.getRepository() != null && entity.getService() == null)
			templateType = 1;
		else if(entity.getService() != null)
			templateType = 2;
		else
			templateType = 0;
	}

	@Override
	public Map<String, Object> getDataModel() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("entity", entity);
		data.put("packageName", getWrapper().getPackageName());
		data.put("count", COUNT_TEMPLATE[templateType]);
		data.put("find", FIND_TEMPLATE[templateType]);
		data.put("findAll", FINDALL_TEMPLATE[templateType]);
		data.put("findEntityEntries", FINDENTITYENTRIES_TEMPLATE[templateType]);
		data.put("flush", FLUSH_TEMPLATE[templateType]);
		data.put("mergeUpdate", MERGEUPDATE_TEMPLATE[templateType]);
		data.put("persist", PERSIST_TEMPLATE[templateType]);
		data.put("remove", REMOVE_TEMPLATE[templateType]);
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
