package io.myoung.sample.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import io.myoung.sample.exception.EncryptException;

@Component
public class AesUtil {
	private Key keySpec;
	private byte[] iv;
	
	public AesUtil() throws UnsupportedEncodingException {
		keySpec = getAESKey();
	}
	
	public Key getAESKey() throws UnsupportedEncodingException {
	    Key keySpec;

	    String key = "1234567890123456";
	    iv = key.getBytes();
	    
	    byte[] keyBytes = new byte[16];
	    byte[] b = key.getBytes("UTF-8");

	    int len = b.length;
	    if (len > keyBytes.length) {
	       len = keyBytes.length;
	    }

	    System.arraycopy(b, 0, keyBytes, 0, len);
	    keySpec = new SecretKeySpec(keyBytes, "AES");

	    return keySpec;
	}
	
	public String encAES(String str) throws EncryptException {
		Key keySpec = this.keySpec;
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

	    try {
	    	Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    	c.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
	    	byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
	    	String enStr = new String(Base64.encodeBase64(encrypted));
	    	 
	    	return enStr;
	    }catch(Exception e) {
	    	throw new EncryptException(e);
	    }
	}

	public String decAES(String enStr) throws EncryptException {
	    Key keySpec = this.keySpec;
	    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
	    try {
		    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    c.init(Cipher.DECRYPT_MODE, keySpec,ivParameterSpec);
		    byte[] byteStr = Base64.decodeBase64(enStr.getBytes("UTF-8"));
		    String decStr = new String(c.doFinal(byteStr), "UTF-8");
	
		    return decStr;
	    }catch(Exception e) {
	    	throw new EncryptException(e);
	    }
	}
}
