package com.peramdy.redis.spring;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * @author peramdy
 * @date 2017/12/19
 */
public class MyCache implements Cache {

    @Ignore
    @Autowired
    protected RedisTemplate redisTemplate;

    private String name;

    public MyCache() {
    }

    public MyCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getNativeCache() {
        return redisTemplate;
    }

    @Nullable
    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper result = null;
        Object obj = redisTemplate.opsForHash().get(name, key);
        if (obj != null) {
            result = new SimpleValueWrapper(obj);
        }
        return result;
    }

    @Nullable
    @Override
    public <T> T get(Object key, @Nullable Class<T> type) {
        T result = null;
        Object obj = redisTemplate.opsForHash().get(name, key);
        if (obj != null) {
            result = (T) result;
        }
        return result;
    }

    @Nullable
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, @Nullable Object value) {
        if (value == null) {
            return;
        }
        redisTemplate.opsForHash().put(name, key, value);
    }

    @Nullable
    @Override
    public ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        redisTemplate.opsForHash().putIfAbsent(name, key, value);
        return null;
    }

    @Override
    public void evict(Object key) {
        redisTemplate.opsForHash().delete(name, key);
    }

    @Override
    public void clear() {
        redisTemplate.delete(name);
    }
}
