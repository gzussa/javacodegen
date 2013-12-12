public void testFindAll${entity.fileName}s() {
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", ${entity.varName}DataOnDemand.getRandom${entity.fileName}());
    long count = ${entity.service.varName}.countAll${entity.fileName}s();
    Assert.assertTrue("Too expensive to perform a find all test for '${entity.fileName}', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
    List<${entity.fileName}> result = ${entity.service.varName}.findAll${entity.fileName}s();
    Assert.assertNotNull("Find all method for '${entity.fileName}' illegally returned null", result);
    Assert.assertTrue("Find all method for '${entity.fileName}' failed to return any data", result.size() > 0);
}