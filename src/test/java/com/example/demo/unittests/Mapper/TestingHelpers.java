package com.example.demo.unittests.Mapper;

public class TestingHelpers {

	public TestingHelpers() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * It returns the test being ran , i use it to log the tests made after the @BeforeEach
	 * annotated function, if not, it returns the test_caller function itself. i firmly believe that there are tools for this in the spring
	 * environment 
	 * @author Aleandro Matteoni
	 * 
	 */
	static String get_method_to_run() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		 if (stackTrace.length > 2) {
	           return stackTrace[2].getMethodName();
	        }
		 else {
			 return stackTrace[1].getMethodName();
		 }
		 
	}
}
