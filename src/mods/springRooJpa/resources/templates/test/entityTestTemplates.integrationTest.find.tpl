public void testFind{{entity.name}}() {
    {{entity.name}} obj = {{dodTest.varName}}.getRandom{{entity.name}}();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", obj);
    Long id = obj.getId();
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to provide an identifier", id);
    obj = {{entity.name}}.find{{entity.name}}(id);
    Assert.assertNotNull("Find method for '{{entity.name}}' illegally returned null for id '" + id + "'", obj);
    Assert.assertEquals("Find method for '{{entity.name}}' returned the incorrect identifier", id, obj.getId());
}