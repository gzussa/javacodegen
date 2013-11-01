package ${entity.packageName};

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
<#if entity.service?? || entity.repository??>
import org.springframework.beans.factory.annotation.Autowired;
</#if>
<#if entity.service??>
import ${entity.service.packageName}.${entity.service.name};
</#if>
<#if entity.repository??>
import ${entity.repository.packageName}.${entity.repository.name};
</#if>

@Component
@Configurable
public class ${entity.fileName}DataOnDemand; {

    private Random rnd = new SecureRandom();

    private List<${entity.fileName}> data;

	<#if entity.service??>
    @Autowired
    ${entity.service.name} ${entity.service.varName};
	</#if>

    <#if entity.repository??>
    @Autowired
    ${entity.repository.name} ${entity.repository.varName};
    </#if>

    public ${entity.fileName} getNewTransient${entity.fileName}(int index) {
        ${entity.fileName} obj = new ${entity.fileName}();
        <#list fields as field>
        set${field.upperName}(obj, index);
        </#list>
        return obj;
    }

    public ${entity.fileName} getSpecific${entity.fileName}(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        ${entity.fileName} obj = data.get(index);
        Long id = obj.getId();
        <#if !entity.service?? && entity.repository??>
        return ${entity.repository.varName}.findOne(id);
        <#elseif entity.service??>
        return ${entity.service.varName}.find${entity.fileName}(id);
        <#else>
        return ${entity.fileName}.find${entity.fileName}(id);
        </#if>
    }

    public ${entity.fileName} getRandom${entity.fileName}() {
        init();
        ${entity.fileName} obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        <#if !entity.service?? && entity.repository??>
        return ${entity.repository.varName}.findOne(id);
        <#elseif entity.service??>
        return ${entity.service.varName}.find${entity.fileName}(id);
        <#else>
        return ${entity.fileName}.find${entity.fileName}(id);
        </#if>
    }

    public boolean modify${entity.fileName}(${entity.fileName} obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        <#if !entity.service?? && entity.repository??>
        data = ${entity.repository.varName}.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        <#elseif entity.service??>
        data = ${entity.service.varName}.find${entity.fileName}Entries(from, to);
        <#else>
        data = ${entity.fileName}.find${entity.fileName}Entries(from, to);
        </#if>
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for '${entity.fileName}' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<${entity.fileName}>();
        for (int i = 0; i < 10; i++) {
            ${entity.fileName} obj = getNewTransient${entity.fileName}(i);
            try {
	            <#if !entity.service?? && entity.repository??>
	            ${entity.repository.varName}.save(obj);
		        <#elseif entity.service??>
		        ${entity.service.varName}.save${entity.fileName}(obj);
		        <#else>
		        obj.persist();
		        </#if>
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
	            <#if entity.repository??>
	            ${entity.repository.varName}.flush();
		        <#else>
		        obj.flush();;
		        </#if>
            data.add(obj);
        }
    }
}