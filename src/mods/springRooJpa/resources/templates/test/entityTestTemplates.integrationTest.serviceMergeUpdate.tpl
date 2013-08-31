public void testUpdate{{entity.name}}Update() {
    {{entity.name}} obj = {{dodTest.varName}}.getRandom{{entity.name}}();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to provide an identifier", id);
    obj = {{service.varName}}.find{{entity.name}}(id);
    boolean modified =  {{dodTest.varName}}.modify{{entity.name}}(obj);
    Integer currentVersion = obj.getVersion();
    {{entity.name}} merged = {{service.varName}}.update{{entity.name}}(obj);
    obj.flush();
    Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
    Assert.assertTrue("Version for '{{entity.name}}' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
}