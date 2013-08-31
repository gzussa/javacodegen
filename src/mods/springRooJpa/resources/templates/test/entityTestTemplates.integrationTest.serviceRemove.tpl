public void testDelete{{entity.name}}() {
    {{entity.name}} obj = {{dodTest.varName}}.getRandom{{entity.name}}();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to provide an identifier", id);
    obj = {{service.varName}}.find{{entity.name}}(id);
    {{service.varName}}.delete{{entity.name}}();
    obj.flush();
    Assert.assertNull("Failed to remove '{{entity.name}}' with identifier '" + id + "'", {{service.varName}}.find{{entity.name}}(id));
}