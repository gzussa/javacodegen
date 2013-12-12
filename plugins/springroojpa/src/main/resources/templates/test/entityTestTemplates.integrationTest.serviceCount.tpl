public void testCountAll${entity.fileName}s() {
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", ${entity.varName}DataOnDemand.getRandom${entity.fileName}());
    long count = ${entity.service.varName}.countAll${entity.fileName}s();
    Assert.assertTrue("Counter for '${entity.fileName}' incorrectly reported there were no entries", count > 0);
}