package it.vitalegi.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Profile("remote")
@Configuration
@Slf4j
public class RemoteConfig {

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration //
                                       .defaultCacheConfig() //
                                       .entryTtl(Duration.ofMinutes(60)) //
                                       .disableCachingNullValues() //
                                       .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder.enableStatistics().withCacheConfiguration("userCache", userCacheConfig())
                                   .withCacheConfiguration("customerCache", customerCacheConfig());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        log.info("Init redisTemplate with jedisConnectionFactory");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

    RedisCacheConfiguration customerCacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(5));
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory(@Value("${redis.host}") String host,
                                                  @Value("${redis.port}") int port) {
        log.info("Init JedisConnectionFactory {}:{}", host, port);
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(6379);
        return jedisConFactory;
    }

    RedisCacheConfiguration userCacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10));
    }

}
