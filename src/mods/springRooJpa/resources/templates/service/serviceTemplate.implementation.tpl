package ${service.packageName};

<#include serviceImplImports>

@Service
@Transactional
public class ${service.name}Impl implements ${service.name} {

    public long countAll${service.name}s() {
        return ${service.name}.count${service.name}s();
    }

    public void delete${service.name}(${service.name} ${varName}) {
        ${varName}.remove();
    }

    public ${service.name} find${service.name}(Long id) {
        return ${service.name}.find${service.name}(id);
    }

    public List<${service.name}> findAll${service.name}s() {
        return ${service.name}.findAll${service.name}s();
    }

    public List<${service.name}> find${service.name}Entries(int firstResult, int maxResults) {
        return ${service.name}.find${service.name}Entries(firstResult, maxResults);
    }

    public void save${service.name}(${service.name} ${varName}) {
        ${varName}.persist();
    }

    public ${service.name} update${service.name}(MyEntity ${varName}) {
        return ${varName}.merge();
    }
}