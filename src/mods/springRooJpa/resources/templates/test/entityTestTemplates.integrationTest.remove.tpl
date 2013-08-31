public void testRemove() {
    {{entity.name}} obj = {{dodTest.varName}}.getRandom{{entity.name}}();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to provide an identifier", id);
    obj = {{entity.name}}.find{{entity.name}}(id);
    obj.remove();
    obj.flush();
    Assert.assertNull("Failed to remove '{{entity.name}}' with identifier '" + id + "'", {{entity.name}}.find{{entity.name}}(id));
}