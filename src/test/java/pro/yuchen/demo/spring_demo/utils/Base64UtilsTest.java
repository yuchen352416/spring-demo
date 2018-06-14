package pro.yuchen.demo.spring_demo.utils;

import org.junit.Test;

public class Base64UtilsTest {

	@Test
	public void testDecode() {
		String s = Base64Utils.decode("dGVzdA==");
		System.out.println(s);

	}

}
