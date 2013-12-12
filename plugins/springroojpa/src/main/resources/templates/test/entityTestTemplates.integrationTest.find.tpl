public void testFind${entity.fileName}() {
    ${entity.fileName} obj = ${entity.varName}DataOnDemand.getRandom${entity.fileName}();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to provide an identifier", id);
    obj = ${entity.fileName}.find${entity.fileName}(id);
    Assert.assertNotNull("Find method for '${entity.fileName}' illegally returned null for id '" + id + "'", obj);
    Assert.assertEquals("Find method for '${entity.fileName}' returned the incorrect identifier", id, obj.getId());
}