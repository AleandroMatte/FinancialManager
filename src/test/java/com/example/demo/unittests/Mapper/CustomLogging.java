package com.example.demo.unittests.Mapper;

import java.util.logging.Logger;

public class CustomLogging{
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

	public static void log_success(Logger logger,String message) {
		logger.info(GREEN + message + GREEN);
		
	}
	
	public static void log_failure(Logger logger,String message) {
		logger.severe(RED + message + RED);
		
	}
	public static void log_info(Logger logger,String message) {
		logger.info(YELLOW + message + YELLOW);
		
	}

}
