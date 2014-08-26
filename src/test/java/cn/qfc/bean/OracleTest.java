package cn.qfc.bean;

import java.math.BigDecimal;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class OracleTest extends JTester {
	
	@Test(groups={"oracle"})
	@SuppressWarnings({ "serial", "unchecked" })
	public void testDataMap() {
		
		db.table("USER2").clean().insert(new DataMap() {
			{
				this.put("USERNAME", "jtester");
				this.put("PASSWORD", "jtester");
				this.put("DATEE", "2011-11-11");
				this.put("BD", new BigDecimal("99.99"));
			}
		}).commit();
		
		db.table("USER2").insert("{'USERNAME':'jtester2','PASSWORD':'jtester2','DATEE':'2013-11-05','BD':'8.88'}").commit();
		
		db.table("USER2").count().eq(2);
		
		db.table("USER2").query().propertyEqMap(2, new DataMap() {
			{
				this.put("BD", new BigDecimal("99.99"), new BigDecimal("8.88"));
				this.put("DATEE", "2011-11-11", "2013-11-05");
				this.put("PASSWORD", "jtester", "jtester2");
				this.put("USERNAME", "jtester", "jtester2");
			}
		});
		
		db.table("USER2").queryWhere("USERNAME='jtester'").propertyEq("PASSWORD", "jtester")
			.propertyEq("DATEE", "2011-11-11").propertyEq("BD", new BigDecimal("99.99"));
	}
	
}
