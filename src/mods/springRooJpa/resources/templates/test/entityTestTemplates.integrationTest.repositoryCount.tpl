public void testCount() {
	Assert.assertNotNull("Data on demand for '{{entity.name}}' failed to initialize correctly", {{dodTest.varName}}.getRandom{{entity.name}}());
	long count = {{repository.varName}}.count();
	Assert.assertTrue("Counter for '{{entity.name}}' incorrectly reported there were no entries", count > 0);
}