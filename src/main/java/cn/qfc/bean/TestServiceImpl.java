package cn.qfc.bean;

public class TestServiceImpl implements ITestService {
	
	private String name;
	public static String password;
	
	static {
		password = "init";
	}
	
	public TestServiceImpl() {
		name = "TestServiceImpl";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String service() {
		
		if(privateMethod()) {
			return "true";
		}
		
		return "false";
	}

	public String service2() {
		
		if(privateMethod2(2)) {
			return "true";
		}
		
		return "false";
	}
	
	public void service3(Phone phone) {
		
	}
	
	private boolean privateMethod() {
		
		return false;
	}
	
	private boolean privateMethod2(int i) {
		
		return false;
	}
	
	public static String staticMethod() {
		
		return "staticMethod";
	}
	
	public static String getPassword() {
		return password;
	}
	
	public boolean service4(ITestService2 testService2) {
		
		Phone phone = testService2.service();
		
		if("jtester".equals(phone.getHome())) {
			return true;
		}
		
		return false;
	}
}
