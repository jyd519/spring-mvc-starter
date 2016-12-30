package ata.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;

public class MD5 {
	private static final transient Logger logger = Logger.getLogger(MD5.class);
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 *
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");

	public static String MD5Encode(String paramStr) {
		String resultString = paramStr;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes(UTF8_CHARSET)));
		} catch (Exception ex) {
		  logger.error("MD5Encode error", ex);
		}
		return resultString;
	}

	public static String MD5Encode(byte[] bytes) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToHexString(md.digest(bytes));
	}

	/*
	 * 生成InputStream的md5校验值
	 */
	public static String MD5Encode (InputStream myfile) throws Exception {
	  MessageDigest md = MessageDigest.getInstance("MD5");
	  byte[] buffer = new byte[4096];
	  int numRead = 0 ;
	  while((numRead = myfile.read(buffer)) >0){
	    md.update(buffer , 0 ,numRead);
	  }
	  myfile.close();
	  return byteArrayToHexString(md.digest());
	}

}
