package pro.yuchen.demo.spring_demo.utils.rsa;

import junit.framework.TestCase;

import java.util.Map;

public class RSA_Test extends TestCase {

	public void testApp() throws Exception {
		String filepath = "~/development/";
		System.out.println("--------------生成公钥私钥-------------------");
		Map<String, String> genkey = RSAEncrypt.genKeyPair(filepath);
		System.out.println("公钥: " + genkey.get("publicKey"));
		System.out.println("私钥: " + genkey.get("privateKey"));

		System.out.println("--------------公钥加密私钥解密过程-------------------");
		String plainText = "ihep_公钥加密私钥解密";
		// 公钥加密过程
		byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(genkey.get("publicKey")), plainText.getBytes());
		String cipher = Base64.encode(cipherData);
		// 私钥解密过程
		byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(genkey.get("privateKey")), Base64.decode(cipher));
		String restr = new String(res);
		System.out.println("原文：" + plainText);
		System.out.println("加密：" + cipher);
		System.out.println("解密：" + restr);
		System.out.println();

		System.out.println("--------------私钥加密公钥解密过程-------------------");
		plainText = "ihep_私钥加密公钥解密";
		// 私钥加密过程
		cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(genkey.get("privateKey")), plainText.getBytes());
		cipher = Base64.encode(cipherData);
		// 公钥解密过程
		res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(genkey.get("publicKey")), Base64.decode(cipher));
		restr = new String(res);
		System.out.println("原文：" + plainText);
		System.out.println("加密：" + cipher);
		System.out.println("解密：" + restr);
		System.out.println();

		System.out.println("---------------私钥签名过程------------------");
		String content = "ihep_这是用于签名的原始数据";
		String signstr = RSASignature.sign(content, genkey.get("privateKey"));
		System.out.println("签名原串：" + content);
		System.out.println("签名串：" + signstr);
		System.out.println();

		System.out.println("---------------公钥校验签名------------------");
		System.out.println("签名原串：" + content);
		System.out.println("签名串：" + signstr);

		System.out.println("验签结果：" + RSASignature.doCheck(content, signstr, genkey.get("publicKey")));
		System.out.println();
	}
}
