package cn.qfc.bean;

import java.math.BigDecimal;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class MySQLAndSQLServerTest extends JTester {
	
	@Test(groups={"mysql", "sqlserver"})
	@SuppressWarnings({ "serial", "unchecked" })
	public void testDataMap() {
		
		db.table("user2").clean().insert(new DataMap() {
			{
				this.put("id", "888");
				this.put("username", "jtester");
				this.put("password", "jtester");
				this.put("datee", "2011-11-11 11:11:11");
				this.put("bd", new BigDecimal("99.99"));
			}
		}).commit();
		
		db.table("user2").insert("{'id':'999','username':'jtester2','password':'jtester2','datee':'2013-11-05 11:11:11','bd':'8.88'}").commit();
		
		db.table("user2").count().eq(2);
		db.table("user2").query().propertyEqMap(2, new DataMap() {
			{
				this.put("bd", new BigDecimal("99.99"), new BigDecimal("8.88"));
				this.put("datee", "2011-11-11 11:11:11", "2013-11-05 11:11:11");
				this.put("password", "jtester", "jtester2");
				this.put("username", "jtester", "jtester2");
			}
		});
	}
	
}
