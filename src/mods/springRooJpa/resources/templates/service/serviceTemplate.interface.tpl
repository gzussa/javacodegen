package ${service.packageName};

<#include serviceInterfaceImports>

public interface ${service.name} {

	public abstract long countAll${name}s();


	public abstract void delete${name}(${name} ${varName});


	public abstract ${name} find${name}(Long id);


	public abstract List<${name}> findAll${name}s();


	public abstract List<${name}> find${name}Entries(int firstResult, int maxResults);


	public abstract void save${name}(${name} ${varName});


	public abstract ${name} update${name}(${name} ${varName});

}
