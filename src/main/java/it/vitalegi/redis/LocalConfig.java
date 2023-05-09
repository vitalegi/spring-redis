package it.vitalegi.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@Profile("local")
@Configuration
@Slf4j
public class LocalConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        var userCache = new ConcurrentMapCache("userCache", new ConcurrentHashMap<>(), true);
        cacheManager.setCaches(Arrays.asList(userCache));
        return cacheManager;
    }

}
