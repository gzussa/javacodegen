public void testFlush() {
    {{entity.name}} obj = {{dodTest.varName}}.getRandom{{entity.name}}();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to provide an identifier", id);
    obj = {{service.varName}}.find{{entity.name}}(id);
    Assert.assertNotNull("Find method for '{{entity.name}}' illegally returned null for id '" + id + "'", obj);
    boolean modified =  {{dodTest.varName}}.modify{{entity.name}}(obj);
    Integer currentVersion = obj.getVersion();
    obj.flush();
    Assert.assertTrue("Version for '{{entity.name}}' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
}