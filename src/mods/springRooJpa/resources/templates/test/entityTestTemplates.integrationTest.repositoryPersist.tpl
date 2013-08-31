public void testSave() {
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", {{dodTest.varName}}.getRandom{{entity.name}}());
    {{entity.name}} obj = {{dodTest.varName}}.getNewTransient{{entity.name}}(Integer.MAX_VALUE);
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to provide a new transient entity", obj);
    Assert.assertNull("Expected '{{entity.name}}' identifier to be null", obj.getId());
    {{repository.varName}}.save(obj);
    {{repository.varName}}.flush();
    Assert.assertNotNull("Expected '{{entity.name}}' identifier to no longer be null", obj.getId());
}