package com.briup.util;

import java.util.Properties;

import org.apache.log4j.Logger;

public class ILog implements Log{

	private static Logger log = null;
//	private static Properties log4j;
	static {
		log = Logger.getRootLogger();
		//PropertyConfigurator.configure("src/main/java/log4j.properties");
	}
	public void init(Properties properties) throws Exception {
		//properties.getProperty("log4j-properties");
	}

	public void debug(String message) {
		log.debug(message);
	}

	public void info(String message) {
		log.info(message);
	}

	public void warn(String message) {
		log.warn(message);
	}

	public void error(String message) {
		log.error(message);
	}

	public void fatal(String message) {
		log.fatal(message);
	}

}
