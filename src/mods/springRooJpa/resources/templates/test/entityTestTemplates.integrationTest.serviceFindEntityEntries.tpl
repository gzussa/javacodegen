public void testFind{{entity.name}}Entries() {
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", {{dodTest.varName}}.getRandom{{entity.name}}());
    long count = {{service.varName}}.countAll{{entity.name}}s();
    if (count > 20) count = 20;
    int firstResult = 0;
    int maxResults = (int) count;
    List<{{entity.name}}> result = {{service.varName}}.find{{entity.name}}Entries(firstResult, maxResults);
    Assert.assertNotNull("Find entries method for '{{entity.name}}' illegally returned null", result);
    Assert.assertEquals("Find entries method for '{{entity.name}}' returned an incorrect number of entries", count, result.size());
}