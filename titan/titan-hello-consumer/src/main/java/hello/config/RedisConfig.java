package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;

@Configuration
public class RedisConfig {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public ValueOperations<String, String> redisString() {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, String> redisList() {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, String> redisSet() {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, String> redisZSet() {
        return redisTemplate.opsForZSet();
    }

    @Bean
    public HashOperations<String, String, String> redisHash() {
        return redisTemplate.opsForHash();
    }
}
