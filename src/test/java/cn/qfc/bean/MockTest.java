package cn.qfc.bean;

import mockit.Cascading;
import mockit.Mock;
import mockit.Mocked;
import mockit.Verifications;
//import mockit.Mocked;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

import cn.qfc.bean.ITestService;
import cn.qfc.bean.ITestService2;
import cn.qfc.bean.Phone;
import cn.qfc.bean.TestServiceImpl;

public class MockTest extends JTester {
	
	/**
	 * 不指定具体的方法则mock全部的方法
	 * 
	 * 使用methods指定具体mock的方法, inverse = true取补集
	 * @Mocked(methods = { "service", "service2" }, inverse = false)
	 * 
	 * 这里的声明会使得mock构造方法失败, 下面均改为使用final的局部变量
	 */
//	@Mocked
//	TestServiceImpl testServiceImpl;
	
	@Mocked
	@Cascading
	ITestService2 testService2;
	
	@Test(priority = -1)
	public void testStaticBlockMock() {
		
		new MockUp<TestServiceImpl>() {
			
			@Mock
			public void $clinit() {
				TestServiceImpl.password = "static";
			}
		};
		
		String password = TestServiceImpl.password;
		
		want.string(password).eq("static");
	}
	
	@Test
	public void testSimpleMock(@Mocked final TestServiceImpl testServiceImpl) {
		
		// 录制期望
		new Expectations(testServiceImpl) {
			{
				testServiceImpl.service();
				times = 2;
				result = "simple";
				
				testServiceImpl.service2();
				result = "simple";
			}
		};
		
		// 执行测试（严格按照录制的顺序和次数进行）
		String service = testServiceImpl.service();
		testServiceImpl.service();
		String service2 = testServiceImpl.service2();
		
		// 验证结果
		want.string(service).eq("simple");
		want.string(service2).eq("simple");
	}
	
	@Test
	public void testPrivateMock(@Mocked final TestServiceImpl testServiceImpl) {
		
		new Expectations(testServiceImpl) {
			{
				invoke(testServiceImpl, "privateMethod");
				result = true;
				
				invoke(testServiceImpl, "privateMethod2", anyInt);
				result = false;
			}
		};
		
		String service = testServiceImpl.service();
		String service2 = testServiceImpl.service2();
		
		want.string(service).eq("true");
		want.string(service2).eq("false");
	}
	
	@Test
	public void testConstructorMock() {
		
		new MockUp<TestServiceImpl>() {
			
			TestServiceImpl it;
			
			@Mock
			public void $init() {
				it.setName("constructor");
			}
		};
		
		String name = new TestServiceImpl().getName();
		
		want.string(name).eq("constructor");
	}
	
	@Test
	public void testStaticMethodMock() {
		
		new Expectations(TestServiceImpl.class) {
			{
				TestServiceImpl.staticMethod();
				times = 1;
				result = "static";
			}
		};
		
		String st = TestServiceImpl.staticMethod();
		
		want.string(st).eq("static");
	}
	
	@Test
	public void testInterface() {
		
		ITestService testService = new MockUp<ITestService>() {
			
			@Mock
			public String service() {
				
				return "interface";
			}
		}.getMockInstance();
		
		String service = testService.service();
		
		want.string(service).eq("interface");
	}
	
	@Test
	public void testParameter() {
		
		new MockUp<TestServiceImpl>() {
			
			@Mock(invocations = 1)
			public void service3(Phone phone) {
				want.object(phone).propertyEq("home", "parameter").propertyEq("office", "parameter");
			}
		};
		
		Phone phone = new Phone();
		phone.setHome("parameter");
		phone.setOffice("parameter");
		new TestServiceImpl().service3(phone);
	}
	
	@Test
	public void testCascading() {
		
		new Expectations() {
			{
				testService2.service().getHome();
				result = "cascading";
				
				testService2.service().getHome();
				result = "jtester";
			}
		};
		
		want.bool(new TestServiceImpl().service4(testService2)).is(false);
		want.bool(new TestServiceImpl().service4(testService2)).is(true);
	}
	
	@Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "exception")
	public void testException() {
		
		new Expectations() {
			{
				testService2.service();
				result = new RuntimeException("exception");
			}
		};
		
		new TestServiceImpl().service4(testService2);
	}
	
	@Test
	public void testVariables(@Mocked final TestServiceImpl testServiceImpl) {
		
		new Expectations() {
			{
				testServiceImpl.service();
				result = "variables";
			}
		};
		
		String service = testServiceImpl.service();
		
		want.string(service).eq("variables");
	}
	
	@Test
	public void testVerifications(@Mocked final TestServiceImpl testServiceImpl) {
		
		new NonStrictExpectations() {
			{
				testServiceImpl.service();
				result = "verifications";
			}
		};
		
		final String result = testServiceImpl.service();
		
		new Verifications() {
			{
				testServiceImpl.service();
				times  = 1;
				minTimes = 1;
				maxTimes = 1;
				want.string(result).eq("verifications");
			}
		};
	}
}
