package ${packageName};

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
<#if entity.service??>
import ${entity.service.packageName}.${entity.service.name};
</#if>
<#if entity.repository??>
import ${entity.repository.packageName}.${entity.repository.name};
</#if>

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class ${entity.fileName}IntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ${entity.fileName}DataOnDemand ${entity.varName}DataOnDemand;

	<#if entity.service??>
	@Autowired
	${entity.service.name} ${entity.service.varName};
	</#if>

    <#if entity.repository??>
    @Autowired
    ${entity.repository.name} ${entity.repository.varName};
    </#if>

	@Test
    <#include count>

	@Test
    <#include find>

	@Test
    <#include findAll>

	@Test
    <#include findEntityEntries>

	@Test
    <#include flush>

	@Test
    <#include mergeUpdate>

	@Test
    <#include persist>

	@Test
   	{{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryRemove.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceRemove.tpl
        else : include entityTestTemplates.integrationTest.remove.tpl
	}}
}