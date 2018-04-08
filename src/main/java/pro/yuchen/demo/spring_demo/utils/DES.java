package pro.yuchen.demo.spring_demo.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.kerberos.KerberosKey;
import javax.security.auth.kerberos.KerberosPrincipal;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


public class DES {

	// 用于产生指定key的常量定义---开始
	private static  String PRINCIPAL_NAME = "yuchen352416@163.com";// 产生key的主体名称

	private static  String KEY_PASSWORD = "yuchen352416@163.com";// key的密码

	private static final String ALGORITHM_NAME = "DES";// 算法名称
	// 用于产生指定的key的常量定义---结束

	// 字符串转16
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// 16转字符串
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * DES加密字符串，返回加密后的字符串
	 *
	 * @param encryptString
	 *            要加密的字符串
	 * @param strings
	 *            不传此参数按默认生成密钥 参数数组，[0]=加密主体名称，[1]=key的密码
	 * @return 返回base64转码后的字符串
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encrypt(String encryptString, String... strings) {
		try {
			Key key = createKey(strings);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			byte[] by = encryptString.getBytes("utf8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 使用私鈅加密
			byte[] cipherText = cipher.doFinal(by);
			return Base64.encode(cipherText).replaceAll("\n", "");
		} catch (Exception e) {
			//Log.error(e);
			return encryptString;
		}
	}

	/**
	 * DES解密字符串
	 *
	 * @param decryptString要解密的字符串
	 * @param strings生成密鑰的參數
	 *            不传此参数按默认生成密钥 参数数组，[0]=加密主体名称，[1]=key的密码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decrypt(String decryptString, String... strings) {
		try {
			Key key = createKey(strings);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			byte[] by = Base64.decode(decryptString);
			cipher.init(Cipher.DECRYPT_MODE, key);// 使用私鈅加密
			byte[] cipherText = cipher.doFinal(by);
			return new String(cipherText, "utf-8");
		} catch (Exception e) {
			return decryptString;
		}
	}

	/**
	 * 创建指定的key，参数数组含义： strings[0]=生成key的主体名称
	 * strings[1]=key的密码（参考命名格式xxx@xx.xxx其他命名格式参考Java Cryptography Architecture
	 * API Specification & Reference 中的附录 A） strings[2]=算法名称如果不传参数按默认值
	 *
	 * @param strings
	 * @return
	 */
	public static Key createKey(String... strings) {
		String algorithm = ALGORITHM_NAME;
		String name = PRINCIPAL_NAME;
		String password = KEY_PASSWORD;
		switch (strings.length) {
			case 1:
				name = strings[0];
				break;
			case 2:
				name = strings[0];
				password = strings[1];
				break;
			case 3:
				name = strings[0];
				password = strings[1];
				algorithm = strings[2];
				break;
		}

		KerberosPrincipal kerberosPrincipal = new KerberosPrincipal(name);
		Key key = new KerberosKey(kerberosPrincipal, password.toCharArray(),
				algorithm);
		return key;
	}

	/**
	 * 产生一个随机key，并保存到文件中
	 *
	 * @param file
	 */
	public static void setPriveKey(String file) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			keyGen.init(56);
			Key key = keyGen.generateKey();// 生成私钥Key
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(key);
			oos.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 从文件中获取key
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static Key getPriveKey(String file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Key key = (Key) ois.readObject();
		return key;
	}
}