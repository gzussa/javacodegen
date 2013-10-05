public void testSave() {
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", ${entity.varName}DataOnDemand.getRandom${entity.fileName}());
    ${entity.fileName} obj = ${entity.varName}DataOnDemand.getNewTransient${entity.fileName}(Integer.MAX_VALUE);
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to provide a new transient entity", obj);
    Assert.assertNull("Expected '${entity.fileName}' identifier to be null", obj.getId());
    ${entity.repository.varName}.save(obj);
    ${entity.repository.varName}.flush();
    Assert.assertNotNull("Expected '${entity.fileName}' identifier to no longer be null", obj.getId());
}