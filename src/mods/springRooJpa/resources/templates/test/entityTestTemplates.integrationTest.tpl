package {{integrationTest.packageName}};

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
{{if service.name != null : print 'import {{service.packageName}}.{{service.name}};'}}
{{if repository.name != null : print 'import {{repository.packageName}}.{{repository.name}};'}}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class {{integrationTest.name}} {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    {{dodTest.name}} {{dodTest.varName}};

    {{if service.name != null : print '@Autowired
    {{service.name}} {{service.varName}};'}}

    {{if repository.name != null : print '@Autowired
    {{repository.name}} {{repository.varName}};'}}

	@Test
    {{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryCount.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceCount.tpl
        else : include entityTestTemplates.integrationTest.count.tpl
	}}

	@Test
    {{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryFind.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceFind.tpl
        else : include entityTestTemplates.integrationTest.find.tpl
	}}

	@Test
    {{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryFindAll.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceFindAll.tpl
        else : include entityTestTemplates.integrationTest.findAll.tpl
	}}

	@Test
    {{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryFindEntityEntries.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceFindEntityEntries.tpl
        else : include entityTestTemplates.integrationTest.findEntityEntries.tpl
	}}

	@Test
    {{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryFlush.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceFlush.tpl
        else : include entityTestTemplates.integrationTest.Flush.tpl
	}}

	@Test
    {{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryMergeUpdate.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceMergeUpdate.tpl
        else : include entityTestTemplates.integrationTest.mergeUpdate.tpl
	}}

	@Test
    {{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryPersist.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.servicePersist.tpl
        else : include entityTestTemplates.integrationTest.persist.tpl
	}}

	@Test
   	{{
    	if repository.name != null AND service.name == null : include entityTestTemplates.integrationTest.repositoryRemove.tpl
        else if service.name != null : include entityTestTemplates.integrationTest.serviceRemove.tpl
        else : include entityTestTemplates.integrationTest.remove.tpl
	}}
}