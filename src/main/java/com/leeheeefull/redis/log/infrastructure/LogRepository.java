package com.leeheeefull.redis.log.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

import static com.leeheeefull.redis.util.RedisKeyConstants.DEFAULT_EXPIRE_DAYS;
import static com.leeheeefull.redis.util.RedisKeyConstants.DEFAULT_KEY_PREFIX;

@RequiredArgsConstructor
@Repository
public class LogRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void deleteAll() {
        for (String s : Objects.requireNonNull(redisTemplate.keys("*"))) {
            redisTemplate.delete(s);
        }
    }

    public void createStringValue(String keySuffix, int count) {
        final String key = generateKey(getNow(), keySuffix);

        redisTemplate.opsForValue()
                .increment(key, count);

        setExpire(key);
    }

    public String getStringValue(String key) {
        Object objectValue = redisTemplate.opsForValue().get(key);

        return Objects.requireNonNull(objectValue)
                .toString();
    }

    private void setExpire(String key) {
        redisTemplate.expire(key, Duration.ofDays(DEFAULT_EXPIRE_DAYS));
    }

    private String generateKey(String time, String keySuffix) {
        return DEFAULT_KEY_PREFIX + time + keySuffix;
    }

    private String getNow() {
        return LocalDate.now()
                .toString()
                .replace("-", "");
    }

}
