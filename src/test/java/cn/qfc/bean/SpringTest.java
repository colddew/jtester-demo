package cn.qfc.bean;

import org.jtester.annotations.SpringApplicationContext;
import org.jtester.annotations.SpringBeanByName;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

import cn.qfc.bean.Phone;

@SpringApplicationContext({"applicationContext-test.xml"})
public class SpringTest extends JTester {
	
	@SpringBeanByName
	private Phone phone;
	
	@Test
	public void testSpringBean() {
		
		want.object(phone).propertyEq("home", "jtester");
		want.object(phone).propertyEq("office", "jtester");
	}
}
