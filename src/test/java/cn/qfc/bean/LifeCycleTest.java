package cn.qfc.bean;

import org.jtester.testng.JTester;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// BeforeSuite -> BeforeTest -> BeforeClass -> BeforeMethod -> [BeforeGroups] -> method -> 
// [AfterGroups] -> AfterMethod -> AfterClass -> AfterTest -> AfterSuite
public class LifeCycleTest extends JTester {
	
	@Test
	public void testDemo1() {
		System.out.println("############### jtester demo1 ###############");
	}
	
	@Test(groups={"lifecycle"})
	public void testDemo2() {
		System.out.println("############### jtester demo2 ###############");
	}
	
	@Test
	public void testDemo3() {
		System.out.println("############### jtester demo3 ###############");
	}
	
	@Test(groups={"lifecycle"})
	public void testDemo4() {
		System.out.println("############### jtester demo4 ###############");
	}
	
	public void testDemo5() {
		System.out.println("############### jtester demo5 ###############");
	}
	
	@BeforeClass
	public void beforeClassMethod() {
		System.out.println("############### before class ###############");
	}
	
	@AfterClass
	public void afterClassMethod() {
		System.out.println("############### after class ###############");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("############### before method ###############");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("############### after method ###############");
	}
	
	@BeforeTest
	public void beforeTestMethod() {
		System.out.println("############### before test ###############");
	}
	
	@AfterTest
	public void afterTestMethod() {
		System.out.println("############### after test ###############");
	}
	
	@BeforeSuite
	public void beforeSuiteMethod() {
		System.out.println("############### before suite ###############");
	}
	
	@AfterSuite
	public void afterSuiteMethod() {
		System.out.println("############### after suite ###############");
	}
	
	@BeforeGroups("lifecycle")
	public void beforeGroupsMethod() {
		System.out.println("############### before groups ###############");
	}
	
	@AfterGroups("lifecycle")
	public void afterGroupsMethod() {
		System.out.println("############### after groups ###############");
	}
}
