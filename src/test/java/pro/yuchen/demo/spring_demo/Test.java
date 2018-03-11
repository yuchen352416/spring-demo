package pro.yuchen.demo.spring_demo;

import pro.yuchen.demo.spring_demo.entity.Book;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Test {

	@org.junit.Test
	public void test() {
//		float f = 3.4f;
//		System.out.println(f);
//		short s1 = 1;
////		s1 = s1 + 1; // 有错...
//		s1 += 1;
//		Integer f1 = 127, f2 = 127, f3 = 128, f4 = 128;
//
//		System.out.println(f1 == f2);
//		System.out.println(f3 == f4);
//		System.out.println(Math.round(-3.6));


//		String s1 = new StringBuilder("fo").append("r").toString();
//		System.out.println(s1.intern() == s1);
//		String s2 = new StringBuilder("ja").append("va").toString();
//		System.out.println(s2.intern() == s2);

		String s1 = "Programming";
		String s2 = new String("Programming");
		String s3 = "Program";
		String s4 = "ming";
		String s5 = "Program" + "ming";
		String s6 = s3 + s4;
		System.out.println(s1 == s2);
		System.out.println(s1 == s5);
		System.out.println(s1 == s6);
		System.out.println(s1 == s6.intern());
		System.out.println(s2 == s2.intern());

		
	}
}
