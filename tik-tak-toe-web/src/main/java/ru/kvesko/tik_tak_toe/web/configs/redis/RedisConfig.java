package ru.kvesko.tik_tak_toe.web.configs.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    private final String hostName;
    private final int port;

    public RedisConfig(
            @Value("${redis.host-name:localhost}") final String hostName,
            @Value("${redis.port:6379}") final int port
    ) {
        this.hostName = hostName;
        this.port = port;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper()));
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions(
            final GameSessionWritingConverter gameSessionWritingConverter,
            final GameSessionReadingConverter gameSessionReadingConverter
    ) {
        return new RedisCustomConversions(Arrays.asList(gameSessionWritingConverter, gameSessionReadingConverter));
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        final var config = new RedisStandaloneConfiguration(this.hostName, this.port);
        return new JedisConnectionFactory(config);
    }

}
