package cn.hx.appium.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理
 * 对原始数据进行AES加密后，在进行Base64编码转化；
 * 正确
 */
public class AseUtil {
	/*已确认
	 * 加密用的Key 可以用26个字母和数字组成
	 * 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private static String sKey="a1ba000c29c04oid";
	private static String ivParameter="40ccdc3b24cc11e7";
	private static AseUtil instance=null;
	//private static 
	private AseUtil(){

	}
	public static AseUtil getInstance(){
		if (instance==null)
			instance= new AseUtil();
		return instance;
	}
	// 加密
	public String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
		return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
	}

	// 解密
	public String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original,encodingFormat);
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		// 需要加密的字串
//		String cSrc = "13510521234";
//		System.out.println("加密前的字串是："+cSrc);
//		// 加密
//		String enString = AseUtil.getInstance().encrypt(cSrc,"utf-8",sKey,ivParameter);
//		System.out.println("加密后的字串是："+ enString);
//
//		System.out.println("2GRyrdOLwF1ly4z/nrnb4w==".equals(enString));
		String enString = "G9a1GCwe0zwmz8wu1AKuCDAkRUbvSby2ivMNQNemIasDUACZyNZkJACx+Qad0mPu0rCqfE6iA+ZdOFeqQ/MUos9UnSgCBpSPDpVjyOpChZleqaxTSZFu1fOIe0AkKx+6YN/iRqcB8oBMrdgrrHhWe9iFPj6BPzn4qPI7IAUbGfy2wCeNyknQbNk7ZGEnoQpC1vHrmXZXhiptZ661ocbyfliUwKiQ/Cy2nGCn5noYzrOEdFG3r1ndgTiIvv8cb3bRZ0p/J3XFTj95uhjZHo2Ua1kYrCqYCYua9eB492O319ZjIEpTtcPSCrpGOLmEFohPCWjId/vVqWwoWPL9RG9n0JmkrnXo2dKHAIeC4YYyVjp5UH1+g/5kHJmoJwjnUX0oL3XKxDhVqL7rBE7tMKTXIA==";
		// 解密
		String DeString = AseUtil.getInstance().decrypt(enString,"utf-8","a1ba000c29c04258","40ccdc3b24cc11e7");
		System.out.println("解密后的字串是：" + DeString);
	}
	
	
}

