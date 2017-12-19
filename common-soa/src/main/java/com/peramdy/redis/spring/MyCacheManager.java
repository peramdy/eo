package com.peramdy.redis.spring;

import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * @author peramdy
 * @dete 2017/12/19.
 */
public class MyCacheManager extends AbstractCacheManager {

    private Collection<? extends MyCache> caches;

    public void setCaches(Collection<? extends MyCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends MyCache> loadCaches() {
        return this.caches;
    }
}
