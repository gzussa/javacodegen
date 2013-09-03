public void testFindAll{{entity.name}}s() {
    Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", {{dodTest.varName}}.getRandom{{entity.name}}());
    long count = {{entity.name}}.count{{entity.name}}s();
    Assert.assertTrue("Too expensive to perform a find all test for '{{entity.name}}', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
    List<{{entity.name}}> result = {{entity.name}}.findAll{{entity.name}}s();
    Assert.assertNotNull("Find all method for '{{entity.name}}' illegally returned null", result);
    Assert.assertTrue("Find all method for '{{entity.name}}' failed to return any data", result.size() > 0);
}