package cn.qfc.bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jtester.hamcrest.matcher.property.reflection.EqMode;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

import cn.qfc.bean.Phone;
import cn.qfc.bean.User;

public class AssertTest extends JTester {
	
	@Test
	public void testString() {
		
		String actual = "qfc-test-cfq";
		want.string(actual).eq("qfc-test-cfq");
		want.string(actual).any();
		want.string(actual).notNull().notBlank();
		want.string(actual).in("qfc-test-cfq", "qfc-test2-cfq");
		want.string(actual).regular("^qfc.*cfq$");
	}
	
	@Test
	public void testBasicType() {
		
		Integer i = new Integer(1);
		Long l = new Long(2);
		Boolean b = Boolean.TRUE;
		Date date2 = new Date(1);
		BigDecimal bd = new BigDecimal(10);
		
		want.number(i).eq(1).isGt(0).isLt(2).isBetween(0, 2);
		want.number(l).eq(2L).isGt(1L).isLt(3L).isBetween(1L, 3L);
		want.bool(b).is(true);
		want.date(date2).eq(new Date(1));
		want.number(bd).eq(new BigDecimal(10)).isGt(new BigDecimal(9))
			.isLt(new BigDecimal(11)).isBetween(new BigDecimal(9), new BigDecimal(11));
	}
	
	@Test
	public void testObject() {
		
		User user = new User();
		user.setUsername("jtester");
		user.setPassword("jtester");
		user.setBd(new BigDecimal(2));
		User user2 = new User();
		user2.setUsername("jtester");
		user2.setPassword("jtester");
		user2.setBd(new BigDecimal(2));
		want.object(user2).eqByReflect(user);
		
		user.setDatee(new Date(1));
		user2.setDatee(new Date(1));
//		want.object(user).eqByReflect(user2);
		want.object(user).propertyMatch("datee", the.date().eq(user2.getDatee()));
		
		String[] strs = new String[] {"123", "456"};
		String[] strs2 = new String[] {"456", "123"};
		want.object(strs2).eqByReflect(strs, EqMode.IGNORE_ORDER);
		
		want.object(user2).propertyEq("username", "jtester");
		want.object(user2).propertyEq("password", "jtester");
		
		Phone phone = new Phone();
		phone.setHome("11111111111");
		phone.setOffice("22222222222");
		user2.setPhone(phone);
		want.object(user2).propertyEq("phone.home", "11111111111");
		want.object(user2).propertyEq("phone.office", "22222222222");
	}
	
	@Test
	@SuppressWarnings("serial")
	public void testMap() {
		
		Map<String, Object> map = new HashMap<String, Object>() {
			{
				this.put("username", "jtester");
				this.put("password", "jtester");
				this.put("amout", new BigDecimal(2));
				this.put("datee", new Date(1));
			}
		};
		
		Map<String, Object> map2 = new HashMap<String, Object>() {
			{
				this.put("datee", new Date(1));
				this.put("amout", new BigDecimal(2));
				this.put("password", "jtester");
				this.put("username", "jtester");
			}
		};
		
		want.map(map).eq(map2);
		want.map(map).sizeEq(4).hasKeys("amout").hasValues(new BigDecimal(2), "jtester");
//		want.map(map).propertyEq("datee", new Date());
		want.map(map).propertyMatch("datee", the.date().eq((Date)map2.get("datee")));
	}
	
	@Test
	@SuppressWarnings("serial")
	public void testCollection() {
		
		String[] strs = new String[] {"123", "456"};
		want.list(strs).sizeEq(2);
		
		List<Integer> list = Arrays.asList(1, 2, 3);
		want.list(list).sizeEq(3);
		
		User user = new User();
		user.setUsername("jtester");
		user.setPassword("jtester");
		user.setBd(new BigDecimal(2));
		user.setDatee(new Date(1));
		User user2 = new User();
		user2.setUsername("jtester");
		user2.setPassword("jtester");
		user2.setBd(new BigDecimal(2));
		user2.setDatee(new Date(2));
		User[] users = new User[] { user, user2};
		want.list(users).propertyEqMap(2, new DataMap() {
			{
				this.put("username", "jtester", "jtester");
				this.put("password", "jtester", "jtester");
				this.put("bd", new BigDecimal(2), new BigDecimal(2));
//				this.put("datee", new Date(1), new Date());
			}
		});
	}
}
