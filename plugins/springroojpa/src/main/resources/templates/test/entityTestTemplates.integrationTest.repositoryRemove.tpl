public void testDelete() {
    ${entity.fileName} obj = ${entity.varName}DataOnDemand.getRandom${entity.fileName}();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to provide an identifier", id);
    obj = ${entity.repository.varName}.findOne(id);
    ${entity.repository.varName}.delete(obj);
    ${entity.repository.varName}.flush();
    Assert.assertNull("Failed to remove '${entity.fileName}' with identifier '" + id + "'", ${entity.repository.varName}.findOne(id));
}