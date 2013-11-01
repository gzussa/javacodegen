public void testCount() {
	Assert.assertNotNull("Data on demand for '${entity.fileName}' failed to initialize correctly", ${entity.varName}DataOnDemand.getRandom${entity.fileName}());
	long count = ${entity.repository.varName}.count();
	Assert.assertTrue("Counter for '${entity.fileName}' incorrectly reported there were no entries", count > 0);
}