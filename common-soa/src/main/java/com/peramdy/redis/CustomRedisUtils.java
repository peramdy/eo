package com.peramdy.redis;

import com.peramdy.utils.SerializeUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author peramdy
 * @date 2017/12/18.
 */
public class CustomRedisUtils {

    private Jedis jedis;

    public CustomRedisUtils(Jedis jedis) {
        this.jedis = jedis;
    }

    public String get(String key) {
        String value = null;
        byte[] valueByte = jedis.get(SerializeUtils.serializeObject(key));
        if (valueByte != null) {
            value = SerializeUtils.deserialize(valueByte, String.class);
        }
        return value;
    }

    public void set(String key, String value, Long offset) {
        byte[] keyByte = SerializeUtils.serializeObject(key);
        byte[] valueByte = SerializeUtils.serializeObject(value);

        if (offset == null || offset == 0) {
            jedis.set(keyByte, valueByte);
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
            jedis.set(keyByte, valueByte, "NX".getBytes(), "PX".getBytes(), offset);

        }

    }

    public String replace(String key, String value, Long offset) {
        if (offset == null || offset == 0) {
            jedis.set(key, value);
        } else {
            jedis.set(key, value, "XX", "PX", offset);
        }
        return jedis.get(key);
    }


    public <T> void set(String key, List<T> list, Long offset) {
        byte[] keyByte = SerializeUtils.serializeObject(key);
        byte[] valueByte = SerializeUtils.serializeList(list);

        if (offset == null || offset == 0) {
            jedis.set(keyByte, valueByte);
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
            jedis.set(keyByte, valueByte, "NX".getBytes(), "PX".getBytes(), offset);

        }

    }


    public <T> List<T> getList(String key, Class<T> clazz) {
        List<T> value = null;
        byte[] valueByte = jedis.get(SerializeUtils.serializeObject(key));
        if (valueByte != null) {
            value = SerializeUtils.deserializeList(valueByte, clazz);
        }
        return value;
    }


}
