package com.peramdy.redis;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author peramdy
 * @date 2017/12/19.
 */
public class GenerateKeyUtil {


    /**
     * >>>>>>>>>>>>> NOTE  <<<<<<<<<<<
     *
     * Thread.currentThread().getStackTrace()
     * 得到当前方法的名字
     * String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
     * getStackTrace（）返回一个表示该线程堆栈转储的堆栈跟踪元素数组。如果该线程尚未启动或已
     * 经终止，则该方法将返回一个零长度数组。如果返回的数组不是零长度的，则其第一个元素代表堆
     * 栈顶，它是该序列中最新的方法调用。最后一个元素代表堆栈底，是该序列中最旧的方法调用。
     * getStackTrace()[0]表示的事getStackTrace方法
     *
     *
     */

    /**
     * generate thread
     *
     * @param t
     * @param params
     * @return
     */
    public synchronized static String generateKey(Thread t, String params) {
        StringBuffer key = new StringBuffer();
        key.append(t.currentThread().getStackTrace()[1].getClassName())
                .append(t.currentThread().getStackTrace()[1].getMethodName())
                .append(t.currentThread().getStackTrace()[1].getLineNumber())
                .append(params);
        String resultKey = generateKey(params);
        return resultKey;
    }

    /**
     * generateKey no params
     *
     * @param t
     * @return
     */
    public synchronized static String generateKey(Thread t) {
        return generateKey(t, "");
    }


    /**
     * generateKey params
     *
     * @param params
     * @return
     */
    public synchronized static String generateKey(String params) {
        MessageDigest md;
        String resultKey = null;
        try {
            md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(params.toString().getBytes());
            resultKey = Base64.encodeBase64String(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultKey;
    }

}
