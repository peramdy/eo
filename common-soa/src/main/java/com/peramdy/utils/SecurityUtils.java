package com.peramdy.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author peramdy
 * @date 2017/12/19.
 */
public class SecurityUtils {

    private final static char[] HEX = "0123456789abcdef".toCharArray();

    /**
     * @param bys
     * @return
     */
    public static String bytesToHexString(byte[] bys) {
        if (bys == null || bys.length == 0) {
            return null;
        }
        char[] chs = new char[bys.length * 2];
        for (int i = 0, offset = 0; i < bys.length; i++) {
            chs[offset++] = HEX[bys[i] >> 4 & 0xf];
            chs[offset++] = HEX[bys[i] & 0xf];
        }
        return new String(chs);
    }


    /**
     * @param bys
     * @return
     */
    public static String bytesToHexString2(byte[] bys) {
        if (bys == null || bys.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bys.length; i++) {
            int v = bys[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }


    /**
     * @param params
     * @return
     */
    public static String encodeMd5(Object params) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bytes = md.digest(params.toString().getBytes());
            result = bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param params
     * @return
     */
    public static String encodeMd52(Object params) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bytes = md.digest(params.toString().getBytes());
            result = bytesToHexString2(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param params
     * @return
     */
    public static String encodeBase64(Object params) {
        String result = Base64.encodeBase64String(params.toString().getBytes());
        return result;
    }


    /**
     * @param params
     * @return
     */
    public static String descodeBase64(String params) {
        byte[] resultBytes = Base64.decodeBase64(params);
        if (resultBytes != null && resultBytes.length > 0) {
            return new String(resultBytes);
        }
        return null;
    }


}
