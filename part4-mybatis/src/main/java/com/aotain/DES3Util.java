package com.aotain;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DES3Util {
    private static final String Algorithm = "DESede";

    public static void main(String[] args) throws IOException {
        String ekdpdw12 = encryptMsg("{“name”:”tom”,”age”:10}", "ekdpdw121213451356313215");
        System.out.println(ekdpdw12);
        String ekdpdw121 = decryptMsg(ekdpdw12, "ekdpdw121213451356313215");
        System.out.println(ekdpdw121);

        System.out.println("ekdpdw121213451356313215".getBytes("UTF-8").length);
    }


    /**
     * @param msg
     * @param key
     * @return String
     * @description ͨ��key��3DES����msg������Base64�Ľ��
     * @author gaojian3
     * @date 2013-9-16 ����2:14:55
     * @version 1.0.0
     */
    public static String encryptMsg(String msg, String key) {

		try {
			if (null == msg || null == key) {
				return null;
			}
			byte[] e = encryptMode(msg.getBytes("UTF8"), key);
            System.out.println(new String(e,"gbk"));
			BASE64Encoder enc = new BASE64Encoder();
			return enc.encode(e);
		} catch (Exception e) {
			return msg;
		}
    }

    /**
     * @param msg
     * @param key
     * @return String
     * @description ͨ��key��3DES����msg������Base64�Ľ��
     * @author gaojian3
     * @date 2013-9-16 ����2:16:41
     * @version 1.0.0
     */
    public static String decryptMsg(String msg, String key) {

        try {
            if (null == msg || null == key) {
                return null;
            }
            BASE64Decoder dec = new BASE64Decoder();
            byte[] d = dec.decodeBuffer(msg);

            return new String(decryptMode(d, key));

        } catch (Exception e) {
            return msg;
        }
    }

    public static byte[] encryptMode(byte[] src, String key) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }



    public static byte[] decryptMode(byte[] src, String key) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey, SecureRandom.getInstance("SHA1PRNG"));
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes("UTF-8");

//        if (key.length > temp.length) {
//            System.arraycopy(temp, 0, key, 0, temp.length);
//        } else {
//            System.arraycopy(temp, 0, key, 0, key.length);
//        }
        return temp;
    }
}


