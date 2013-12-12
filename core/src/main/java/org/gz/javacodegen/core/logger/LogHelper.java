package org.gz.javacodegen.core.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class LogHelper{

	private static final Marker DEBUG_MARKER = MarkerManager.getMarker("DEBUG");
	private static final Marker INFO_MARKER = MarkerManager.getMarker("INFO");
	private static final Marker WARN_MARKER = MarkerManager.getMarker("WARN");
	private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");
	private static final Marker FATAL_MARKER = MarkerManager.getMarker("FATAL");
	
	private static Logger logger;
	 
	public LogHelper(String name) {
		logger = LogManager.getLogger(name);
	}
	
	public Logger getLogger(){
		return logger;
	}

	public void debug(String msg){
		logger.debug(DEBUG_MARKER, msg);
	}
	
	public void info(String msg){
		logger.info(INFO_MARKER, msg);
	}
	
	public void warn(String msg){
		logger.warn(WARN_MARKER, msg);
	}
	
	public void error(String msg){
		logger.error(ERROR_MARKER, msg);
	}
	
	public void fatal(String msg){
		logger.fatal(FATAL_MARKER, msg);
	}
}
