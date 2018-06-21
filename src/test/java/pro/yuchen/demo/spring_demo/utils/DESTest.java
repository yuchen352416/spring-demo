package pro.yuchen.demo.spring_demo.utils;

import org.junit.Test;

public class DESTest {

	@Test
	public void test() {
		String encrypt = DES.encrypt("yuchen35241");
		System.out.println(encrypt);
		String decrypt = DES.decrypt(encrypt);
		System.out.println(decrypt);

	}
}
