package cn.qfc.bean;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

import cn.qfc.bean.ErrorConstructor;

public class ReflectorTest extends JTester {
	
	@Test
	public void testPrivateMethod() {
		
		String str = reflector.invoke(new ErrorConstructor(), "doSomething", 1);
		
		want.string(str).eq("doSomething");
	}
	
	@Test
	public void testStaticMethod() {
		
		String str = reflector.invokeStatic(ErrorConstructor.class, "doSomething2");
		
		want.string(str).eq("doSomething2");
	}
	
	@Test
	public void testPrivateField() {
		
		ErrorConstructor ec = new ErrorConstructor();
		reflector.setField(ec, "name", "field");
		
		String name = reflector.getField(ec, "name");
		
		want.string(name).eq("field");
	}
	
	@Test
	@SuppressWarnings("serial")
	public void testConstructor() {
		
		ErrorConstructor instance = reflector.newInstance(
				"{name:'test', age:30, grade: 5, isFemal: false}", ErrorConstructor.class);
		
		reflector.setField(instance, "name", "jtester");
		
		want.object(instance).propertyEqMap(new DataMap() {
			{
				this.put("name", "jtester");
				this.put("age", 30);
				this.put("grade", 5);
				this.put("isFemale", false);
			}
		});
		
		String result = reflector.invoke(instance, "sayHello", "jtester");
		want.string(result).eq("hello, jtester");
	}
}
