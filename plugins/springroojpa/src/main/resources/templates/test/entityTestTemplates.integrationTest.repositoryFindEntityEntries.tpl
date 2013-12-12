public void testFind${entity.fileName}Entries() {
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", ${entity.varName}DataOnDemand.getRandom${entity.fileName}());
    long count = ${entity.repository.varName}.count();
    if (count > 20) count = 20;
    int firstResult = 0;
    int maxResults = (int) count;
    List<${entity.fileName}> result = ${entity.repository.varName}.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    Assert.assertNotNull("Find entries method for '${entity.fileName}' illegally returned null", result);
    Assert.assertEquals("Find entries method for '${entity.fileName}' returned an incorrect number of entries", count, result.size());
}