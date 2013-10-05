public void testFlush() {
    ${entity.fileName} obj = ${entity.varName}DataOnDemand.getRandom${entity.fileName}();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to provide an identifier", id);
    obj = ${entity.service.varName}.find${entity.fileName}(id);
    Assert.assertNotNull("Find method for '${entity.fileName}' illegally returned null for id '" + id + "'", obj);
    boolean modified =  ${entity.varName}DataOnDemand.modify${entity.fileName}(obj);
    Integer currentVersion = obj.getVersion();
    obj.flush();
    Assert.assertTrue("Version for '${entity.fileName}' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
}