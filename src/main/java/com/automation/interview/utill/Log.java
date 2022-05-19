package com.automation.interview.utill;

import org.testng.log4testng.Logger;

public class Log {
	private static Logger log = Logger.getLogger(Logger.class);

	public static void startTestcase(String testcaseName) {
		log.info("-----------------------------------------");
		log.info("**********  Starting Test Case of \"" + testcaseName + "\"  ************\n");
	}

	public static void endTestcase(String testcaseName) {
		log.info("**********  End of Test Case: \"" + testcaseName + "\"  ************\n");
		log.info("-----------------------------------------\n");
	}

	public static void info(String message) {
		log.info(message+"\n");
	}

	public static void warn(String message) {
		log.warn(message+"\n");
	}

	public static void error(String message) {
		log.error(message+"\n");
	}

	public static void fatal(String message) {
		log.fatal(message+"\n");
	}

	public static void debug(String message) {
		log.debug(message+"\n");
	}

}
