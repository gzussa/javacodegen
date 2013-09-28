public void testCountAll{{entity.name}}s() {
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", {{dodTest.varName}}.getRandom{{entity.name}}());
    long count = {{service.varName}}.countAll{{entity.name}}s();
    Assert.assertTrue("Counter for '{{entity.name}}' incorrectly reported there were no entries", count > 0);
}