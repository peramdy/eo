package com.peramdy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author peramdy
 * @date 2017/12/16.
 */
@Component("redisManagerService")
public class RedisManager<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object getValueForHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public <T> Map<String, T> getValueMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void putValue(String key, T t) {
        redisTemplate.opsForValue().set(key, t);
    }

}
