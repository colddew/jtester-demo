package cn.qfc.bean;

public class ErrorConstructor {
	
	private String name;
	private Integer age;
	private Integer grade;
	private boolean isFemale;
	
	public ErrorConstructor() {
		
	}
	
	public ErrorConstructor(String arg1, String arg2) {
		throw new RuntimeException("error");
	}

	@SuppressWarnings("unused")
	private String sayHello(String name) {
		return "hello, " + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public boolean isFemale() {
		return isFemale;
	}

	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}
	
	@SuppressWarnings("unused")
	private String doSomething(int i) {
		
		return "doSomething";
	}
	
	public static String doSomething2() {
		
		return "doSomething2";
	}
}