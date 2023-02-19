package com.leeheeefull.redis.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisProvider {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    public void save(String key, Object value) {
        redisTemplate.opsForValue()
                .set(key, value);

        log.info("key : {}, value : {}", key, value);
    }

    public void save(String key, Object value, int expiry) {
        redisTemplate.opsForValue()
                .set(key, value);
        redisTemplate.expire(key, Duration.ofSeconds(expiry));

        log.info("key : {}, value : {}, expiry : {}", key, value, expiry);
    }

    public void remove(String key) {
        try {
            redisTemplate.delete(key);
            log.info("key : {}", key);
        } catch (Exception e) {
            log.error("key : {}", key);
        }
    }

    public <T> Optional<T> find(String key, Class<T> tClass) {
        Object value = redisTemplate.opsForValue()
                .get(key);
        if (value == null) {
            log.info("key : {} is not exists", key);
            return Optional.empty();
        }

        T result = objectMapper.convertValue(value, tClass);
        log.info("key : {}, value : {}", key, value);
        return Optional.of(result);
    }

    public Boolean exists(String key) {
        Boolean exists = redisTemplate.hasKey(key);
        log.info("is exists : {}", exists);
        return exists;
    }

    public void saveAsync(String key, Object value) {
        reactiveRedisTemplate.opsForValue()
                .set(key, value)
                .doOnSuccess(aBoolean -> log.info("key : {}, value : {}", key, value))
                .doOnError(throwable -> log.error("key : {}, value : {}", key, value))
                .subscribe();
    }
}
