package com.peramdy.redis;

import com.peramdy.utils.SerializeUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author peramdy
 * @date 2017/12/18.
 */
public class CustomRedisUtils {

    /**
     * >>>>>>>>>>>>>>>>>>>>>>>> NOTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     * 使用protobuf时，将string写入redis然后再读出来反序列化protobuf message的时候报错：
     * While parsing a protocol message, the input ended unexpectedly in the middle of a field.
     * This could mean either than the input has been truncated or that an embedded message misreported its own length.
     * <p>
     * 解决办法1:使用iso8859-1编码
     * 解决办法2：使用base64编码传输
     * protostuff 没有
     */


    private Jedis jedis;

    public CustomRedisUtils(Jedis jedis) {
        this.jedis = jedis;
    }


    /**
     * getValue
     *
     * @param key
     * @return
     */
    public String get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String valueStr = jedis.get(GenerateKeyUtil.generateKey(key));
        String value = SerializeUtils.deserialize(Base64.decodeBase64(valueStr), String.class);
        return value;
    }

    /**
     * setValue
     *
     * @param key
     * @param value
     * @param offset
     */
    public void set(String key, String value, Long offset) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        String keyStr = GenerateKeyUtil.generateKey(key);
        String valueStr = Base64.encodeBase64String(SerializeUtils.serializeObject(value));
        if (offset == null || offset == 0) {
            jedis.set(keyStr, valueStr);
        } else {
            /**
             * 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖。
             *
             * @param key
             * @param value
             * @param nxxx  nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
             * @param expx expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
             * @param time 过期时间，单位是expx所代表的单位。
             *
             * String set(String key, String value, String nxxx, String expx, long time);
             */
            jedis.set(keyStr, valueStr, "NX", "PX", offset);

        }

    }

    /**
     * replaceValue
     *
     * @param key
     * @param value
     * @param offset
     */
    public void replace(String key, String value, Long offset) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }
        String keyStr = GenerateKeyUtil.generateKey(key);
        String valueStr = Base64.encodeBase64String(SerializeUtils.serializeObject(value));
        if (offset == null || offset == 0) {
            jedis.set(keyStr, valueStr);
        } else {
            jedis.set(keyStr, valueStr, "XX", "PX", offset);
        }
    }


    /**
     * setListValue
     *
     * @param key
     * @param list
     * @param offset
     * @param <T>
     */
    public <T> void set(String key, List<T> list, Long offset) {

        if (StringUtils.isBlank(key) || list == null || list.size() < 1) {
            return;
        }

        String keyStr = GenerateKeyUtil.generateKey(key);
        String valueStr = null;
        byte[] byteList = SerializeUtils.serializeList(list);
        try {
            valueStr = new String(byteList, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (offset == null || offset == 0) {
            jedis.set(keyStr, valueStr);
        } else {
            /**
             * 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖。
             *
             * @param key
             * @param value
             * @param nxxx  nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
             * @param expx expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
             * @param time 过期时间，单位是expx所代表的单位。
             *
             * String set(String key, String value, String nxxx, String expx, long time);
             */
            jedis.set(keyStr, valueStr, "NX", "PX", offset);
        }
    }


    /**
     * getListValue
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String key, Class<T> clazz) {

        if (StringUtils.isBlank(key)) {
            return null;
        }
        String keyStr = GenerateKeyUtil.generateKey(key);
        List<T> value = null;
        String valueStr = jedis.get(keyStr);
        if (valueStr != null) {
            try {
                byte[] bytes = valueStr.getBytes("UTF-8");
                value = SerializeUtils.deserializeList(bytes, clazz);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }


}
