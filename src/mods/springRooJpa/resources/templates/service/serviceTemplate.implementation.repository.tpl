package ${service.packageName};

<#include serviceImplImports>

@Service
@Transactional
public class ${service.name}Impl implements ${service.name} {

	@Autowired
    ${repository.name} ${repository.varName};

	public long countAll${name}s() {
        return ${repository.varName}.count();
    }

	public void delete${name}(${name} ${varName}) {
        ${repository.varName}.delete(${varName});
    }

	public ${name} find${name}(Long id) {
        return ${repository.varName}.findOne(id);
    }

	public List<${name}> findAll${name}s() {
        return${repository.varName}.findAll();
    }

	public List<${name}> find${name}Entries(int firstResult, int maxResults) {
        return ${repository.varName}.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void save${name}(${name} ${varName}) {
        ${repository.varName}.save(${varName});
    }

	public ${name} update${name}(${name}y ${varName}) {
        return ${repository.varName}.save(${varName});
    }
}
