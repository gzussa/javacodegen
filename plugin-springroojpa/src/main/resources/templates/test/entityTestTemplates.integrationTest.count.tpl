public void testCount{{entity.name}}s() {
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", {{dodTest.varName}}.getRandom{{entity.name}}());
    long count = {{entity.name}}.count{{entity.name}}s();
    Assert.assertTrue("Counter for '{{entity.name}}' incorrectly reported there were no entries", count > 0);
}