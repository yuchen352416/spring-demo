package pro.yuchen.demo.spring_demo.utils;


import sun.misc.BASE64Decoder;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Base64;

public class Base64Utils {

	private Base64Utils() {}

	/**
	 * 加密
	 * @param s 明文
	 * @return 密文
	 */
	public static String encoder(String s) {
		try {
			return Base64.getEncoder().encodeToString(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密
	 * @param s 密文
	 * @return 明文
	 */
	public static String decode(String s) {
		byte[] bytes = Base64.getDecoder().decode(s);
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据 Base64 生成文件
	 * @param base64 
	 * @param path 文件路径
	 * @return 
	 */
	public static boolean generateFile(String base64, String path) {
		if (StringUtils.isEmpty(base64) || StringUtils.isEmpty(path)) {
			return false;
		}
		File file = new File(path);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(base64);
			for (int i = 0; i < bytes.length; ++i) {
				// 调整异常数据
				if (bytes[i] < 0) { bytes[i] += 256; }
			}
			OutputStream out = new FileOutputStream(path);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
