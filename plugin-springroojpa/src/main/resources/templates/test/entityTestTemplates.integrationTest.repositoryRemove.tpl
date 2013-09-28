public void testDelete() {
    {{entity.name}} obj = {{dodTest.varName}}.getRandom{{entity.name}}();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to provide an identifier", id);
    obj = {{repository.varName}}.findOne(id);
    {{repository.varName}}.delete(obj);
    {{repository.varName}}.flush();
    Assert.assertNull("Failed to remove '{{entity.name}}' with identifier '" + id + "'", {{repository.varName}}.findOne(id));
}