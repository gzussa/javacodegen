public void testSaveUpdate() {
    ${entity.fileName} obj = ${entity.varName}DataOnDemand.getRandom${entity.fileName}();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to provide an identifier", id);
    obj = ${entity.repository.varName}.findOne(id);
    boolean modified =  ${entity.varName}DataOnDemand.modify${entity.fileName}(obj);
    Integer currentVersion = obj.getVersion();
    ${entity.fileName} merged = ${entity.repository.varName}.merge(obj);
    ${entity.repository.varName}.flush();
    Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
    Assert.assertTrue("Version for '${entity.fileName}' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
}