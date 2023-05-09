package it.vitalegi.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.CacheStatistics;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile("remote")
@Slf4j
@Component
public class RedisCacheStatisticsService {

    @Autowired
    CacheManager cacheManager;

    @Scheduled(fixedDelay = 5000)
    void stats() {
        log.info("retrieve statistics");
        cacheManager.getCacheNames().forEach(this::stats);
    }

    void stats(String cacheName) {
        RedisCache cache = (RedisCache) cacheManager.getCache(cacheName);
        CacheStatistics s = cache.getStatistics();
        log.info("CACHE_STATISTICS name={}, hits={}, deletes={}, gets={}, misses={}, puts={}", cacheName, s.getHits()
                , s.getDeletes(), s.getGets(), s.getMisses(), s.getPuts());
    }
}
