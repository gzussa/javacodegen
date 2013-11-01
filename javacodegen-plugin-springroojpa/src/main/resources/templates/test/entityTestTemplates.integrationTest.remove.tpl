public void testRemove() {
    ${entity.fileName} obj = ${entity.varName}DataOnDemand.getRandom${entity.fileName}();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to provide an identifier", id);
    obj = ${entity.fileName}.find${entity.fileName}(id);
    obj.remove();
    obj.flush();
    Assert.assertNull("Failed to remove '${entity.fileName}' with identifier '" + id + "'", ${entity.fileName}.find${entity.fileName}(id));
}