package org.gz.javacodegen.core.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LogConfigurator {
	
	public static void initLogger(boolean isDebug){
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    	Configuration config = ctx.getConfiguration();
    	LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        if(isDebug){
        	loggerConfig.setLevel(Level.DEBUG);
        }else{
        	loggerConfig.setLevel(Level.INFO);
        }
        ctx.updateLoggers();  // This causes all Loggers to refetch information from their LoggerConfig.
	}
	
	public static LogHelper getLogger(String name){
		return new LogHelper(name);
	}
}
