package cn.qfc.bean;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class SuiteTest2 extends JTester {
	@Test(groups={"g1"})
	public void testDemo1() {
		System.out.println("############### jtester demo21 ###############");
	}
	
	@Test(groups={"g2"})
	public void testDemo2() {
		System.out.println("############### jtester demo22 ###############");
	}
	
	@Test(groups={"g3"})
	public void testDemo3() {
		System.out.println("############### jtester demo23 ###############");
	}
}