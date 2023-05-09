package it.vitalegi.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.embedded.RedisServer;

@Profile("local")
@Configuration
@Slf4j
public class LocalConfig {

    @Bean
    LettuceConnectionFactory redisConnectionFactory(@Value("${redis.host}") String host,
                                                    @Value("${redis.port}") int port) {
        log.info("Init LettuceConnectionFactory {}:{}", host, port);
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    RedisServer redisServer(@Value("${redis.port}") int port) {
        log.info("Initialize dummy RedisServer");
        var redisServer = new RedisServer(port);
        redisServer.start();
        return redisServer;
    }

    @Bean
    RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
        log.info("Init redisTemplate with lettuce");
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

}
