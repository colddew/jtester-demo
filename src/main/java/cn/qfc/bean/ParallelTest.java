package cn.qfc.bean;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class ParallelTest extends JTester {

	@Test(threadPoolSize = 10, invocationCount = 100, timeOut = 10000)
	public void testParallel() {
		
		String actual = "qfc-test-cfq";
		want.string(actual).eq("qfc-test-cfq");
	}
}
