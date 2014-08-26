package cn.qfc.bean;

import org.jtester.testng.JTester;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloWorldTest extends JTester {
	
	@Test
	public void testHelloWorld() {
		Assert.assertEquals("hello world", "hello world");
		System.out.println("hello world");
	}
}
