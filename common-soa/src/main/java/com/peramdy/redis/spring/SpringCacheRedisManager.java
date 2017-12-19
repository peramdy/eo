package com.peramdy.redis.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.Collection;

/**
 * @author peramdy
 * @date 2017/12/18.
 */
public class SpringCacheRedisManager extends SimpleCacheManager {


    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    public CacheManager redisCacheManager() {
        RedisCacheManager cacheManager = RedisCacheManager.create(jedisConnectionFactory);
        return cacheManager;
    }

    @Override
    public void setCaches(Collection<? extends Cache> caches) {
        super.setCaches(caches);
    }
}
