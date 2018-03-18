package pro.yuchen.demo.spring_demo;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Random;

public class Test_1 {
	String str = "";

	@Before
	public void setup() {
		Random rand = new Random();
		for(int i = 0; i < 20; i++) {
			int r = Math.abs(rand.nextInt());
			str += String.valueOf(r);
			str += " ";
		}
		str.substring(0, str.length() -1);
	}

	@Test
	public void sort() {
		String[] arr = str.split(" ");

		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < i; j++) {
				if (getVal(arr[i]) < getVal(arr[j])) {
					String temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for (String item : arr) {
			System.out.println(item);
		}
	}

	private int getVal(String str) {
		Integer val = 0;
		try {
			if (str.length() > 3) {
				val = Integer.valueOf(str.substring(str.length() - 3));
			} else {
				val = Integer.valueOf(str);
			}
		} catch (Exception e) {
			System.out.println("非数字...");
		}
		return val;
	}
}
