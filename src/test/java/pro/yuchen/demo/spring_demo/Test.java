package pro.yuchen.demo.spring_demo;


import pro.yuchen.demo.spring_demo.utils.DateUtils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

public class Test {

	@org.junit.Test
	public void test() throws UnsupportedEncodingException, ParseException {
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

//		String s1 = "Programming";
//		String s2 = new String("Programming");
//		String s3 = "Program";
//		String s4 = "ming";
//		String s5 = "Program" + "ming";
//		String s6 = s3 + s4;
//		System.out.println(s1 == s2);
//		System.out.println(s1 == s5);
//		System.out.println(s1 == s6);
//		System.out.println(s1 == s6.intern());
//		System.out.println(s2 == s2.intern());

//		String str = "1.2";
//		float f = Float.parseFloat(str);
//		System.out.println(f);

//		String s1 = "你好";
//		String s2 = new String(s1.getBytes("GB2312"), "ISO-8859-1");
//		System.out.println(s2);

//		try {
//			System.out.println("1");
//			return;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("2");
//		}

//		int i = 0;
//		int a = 1 + i++;
//		int b = ++i;
//		System.out.println(a);
//		System.out.println(b);
//
//		System.out.println(2 << 3);

//		System.out.println("12".split(",").length);

//		System.out.println(new Date().getTime());

//		Map<String, Object> treeMap = new TreeMap<>();
//		Map<String, Object> hashMap = new HashMap<>();
//
//		for (int i = 0; i < 100; i++) {
//			treeMap.put("" + i, i);
//		}
//
//		for (Object o : treeMap.values()) {
//			System.out.println(o);
//		}
//
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//
//
//		for (Object o : hashMap.values()) {
//			System.out.println(o);
//		}
//		System.out.println(((7 & 4) == 4) && ((7 & 2) == 2));

//
//		System.out.println(7 & -1);

//		String s = String.format("%6d",1234567).replace(" ", "0");//其中0表示补零而不是补空格，6表示至少6位
//		System.out.println(s);


//		String month = "2018-08";
//		Date date = DateUtils.stringToDate(month, "yyyy-MM");
//		long l = DateUtils.getAddMonth(date.getTime(), 1);
//		Date e = new Date(l);
//		String end = DateUtils.dateToString(e, "yyyy-MM");
//		System.out.println(end);

//		String[] heads = new String[] {"日期", "订单号", "客户ID", "编号", "国家", "签证类型", "客人姓名", "数量", "支出合计", "收入合计", "利润", "操作", "备注"};
//
//		StringBuffer fileContent = new StringBuffer();
//		// 写入表头
//		for(int i = 0; i < heads.length; i++) {
//			fileContent.append(heads[i]);
//			if (i < heads.length - 1) {
//				fileContent.append(",");
//			} else {
//				fileContent.append("\n");
//			}
//		}
//
//		System.out.println(fileContent.toString());


		String foo = "204633853285612014";
		boolean b = Pattern.matches("\\d{8,}", foo);
		System.out.println(b);
	}
}
