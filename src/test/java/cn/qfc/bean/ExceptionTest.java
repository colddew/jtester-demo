package cn.qfc.bean;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class ExceptionTest extends JTester {
	
	@Test(expectedExceptions = RuntimeException.class, 
			expectedExceptionsMessageRegExp = "this is a exception")
	public void testException() {
		
		throw new RuntimeException("this is a exception");
	}
}
