package ${repository.packageName};

<#include repositoryImports>

@Repository
public interface ${repository.name} extends JpaRepository<${name}, Long>, JpaSpecificationExecutor<${name}> {
}