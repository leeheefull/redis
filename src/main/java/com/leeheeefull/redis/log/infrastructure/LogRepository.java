package com.leeheeefull.redis.log.infrastructure;

import com.leeheeefull.redis.log.domain.LogContent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Objects;

import static com.leeheeefull.redis.util.RedisKeyUtils.DEFAULT_EXPIRE_DAYS;
import static com.leeheeefull.redis.util.RedisKeyUtils.generateKey;
import static com.leeheeefull.redis.util.TimeUtils.getNow;

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
        String key = generateKey(getNow(), keySuffix);

        redisTemplate.opsForValue()
                .increment(key, count);

        setExpire(key);
    }

    public String getStringValue(String time, String keySuffix) {
        Object objectValue = redisTemplate.opsForValue()
                .get(generateKey(time, keySuffix));

        return Objects.requireNonNull(objectValue)
                .toString();
    }

    public void createHashValue(String keySuffix, String hashKey, LogContent hashValue) {
        String key = generateKey(getNow(), keySuffix);
        HashOperations<String, String, LogContent> hashOperations = redisTemplate.opsForHash();

        setExpire(key);
        hashOperations.put(key, hashKey, hashValue);
    }

    public LogContent getHashValue(String time, String keySuffix, String hashKey) {
        HashOperations<String, String, LogContent> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(generateKey(time, keySuffix), hashKey);
    }

    private void setExpire(String key) {
        redisTemplate.expire(key, Duration.ofDays(DEFAULT_EXPIRE_DAYS));
    }

}
