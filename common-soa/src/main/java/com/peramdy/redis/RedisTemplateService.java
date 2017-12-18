package com.peramdy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author peramdy
 * @date 2017/12/18.
 */
@Service
public class RedisTemplateService {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object getValueForHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public Map<String, Object> getValueMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void putValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

}
