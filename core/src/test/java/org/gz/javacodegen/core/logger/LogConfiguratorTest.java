package org.gz.javacodegen.core.logger;

import org.junit.Test;

import junit.framework.TestCase;

public class LogConfiguratorTest extends TestCase {
	LogHelper logger = LogConfigurator.getLogger(LogConfiguratorTest.class.getName());
	
	@Test
	public void testDebugEnableMode() throws InterruptedException{
		LogConfigurator.initLogger(true);
		assertTrue(!logger.getLogger().isTraceEnabled());
		logger.getLogger().trace("Message -2");
		logger.getLogger().error("Message -1");
		Thread.sleep(1);
		assertTrue(logger.getLogger().isDebugEnabled());
		logger.debug("Message 0");
		Thread.sleep(1);
		assertTrue(logger.getLogger().isInfoEnabled());
		logger.info("Message 1");
		Thread.sleep(1);
		assertTrue(logger.getLogger().isWarnEnabled());
		logger.warn("Message 2");
		Thread.sleep(1);
		assertTrue(logger.getLogger().isErrorEnabled());
		logger.error("Message 3");
		Thread.sleep(1);
		assertTrue(logger.getLogger().isFatalEnabled());
		logger.fatal("Message 4");
		Thread.sleep(1);
		
		LogConfigurator.initLogger(false);
		assertTrue(!logger.getLogger().isTraceEnabled());
		logger.getLogger().trace("Message -3");
		assertTrue(!logger.getLogger().isDebugEnabled());
		logger.debug("Message -4");
		assertTrue(logger.getLogger().isInfoEnabled());
		logger.info("Message 5");
		assertTrue(logger.getLogger().isWarnEnabled());
		logger.warn("Message 6");
		assertTrue(logger.getLogger().isErrorEnabled());
		logger.error("Message 7");
		assertTrue(logger.getLogger().isFatalEnabled());
		logger.fatal("Message 8");
	}
}
