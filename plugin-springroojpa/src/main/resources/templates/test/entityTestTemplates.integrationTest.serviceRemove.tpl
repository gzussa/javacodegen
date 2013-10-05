public void testDelete${entity.fileName}() {
    ${entity.fileName} obj = ${entity.varName}DataOnDemand.getRandom${entity.fileName}();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to provide an identifier", id);
    obj = ${entity.service.varName}.find${entity.fileName}(id);
    ${entity.service.varName}.delete${entity.fileName}();
    obj.flush();
    Assert.assertNull("Failed to remove '${entity.fileName}' with identifier '" + id + "'", ${entity.service.varName}.find${entity.fileName}(id));
}