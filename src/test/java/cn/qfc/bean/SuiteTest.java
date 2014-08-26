package cn.qfc.bean;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class SuiteTest extends JTester {
	@Test(groups={"g1"})
	public void testDemo1() {
		System.out.println("############### jtester demo1 ###############");
	}
	
	@Test(groups={"g2"})
	public void testDemo2() {
		System.out.println("############### jtester demo2 ###############");
	}
	
	@Test(groups={"g1"})
	public void testDemo3() {
		System.out.println("############### jtester demo3 ###############");
	}
}