package com.leeheeefull.redis.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void 문자열_테스트() {
        // given
        var valueOperations = redisTemplate.opsForValue();
        var key = "stringKey";

        // when
        valueOperations.set(key, "hello");

        // then
        var value = valueOperations.get(key);
        assertThat(value).isEqualTo("hello");
    }


    @Test
    void 집합_테스트() {
        // given
        var setOperations = redisTemplate.opsForSet();
        var key = "setKey";

        // when
        setOperations.add(key, "h", "e", "l", "l", "o");

        // then
        var members = setOperations.members(key);
        var size = setOperations.size(key);

        assertThat(members).containsOnly("h", "e", "l", "o");
        assertThat(size).isEqualTo(4);
    }

    @Test
    void 해시_테스트() {
        // given
        var hashOperations = redisTemplate.opsForHash();
        var key = "hashKey";

        // when
        hashOperations.put(key, "hello", "world");

        // then
        var value = hashOperations.get(key, "hello");
        assertThat(value).isEqualTo("world");

        var entries = hashOperations.entries(key);
        assertThat(entries.keySet()).containsExactly("hello");
        assertThat(entries.values()).containsExactly("world");

        var size = hashOperations.size(key);
        assertThat(size).isEqualTo(entries.size());
    }

    @Test
    void 만료시간_테스트() throws InterruptedException {
        var valueOperations = redisTemplate.opsForValue();
        final String key = "java";
        valueOperations.set(key, "ggul jam");

        // 만료시간 설정
        var expire = redisTemplate.expire(key, 5, TimeUnit.SECONDS);

        // 6초 대기
        Thread.sleep(6000);

        var actual = valueOperations.get(key);
        assertThat(expire).isTrue();
        assertThat(actual).isNull();
    }

}
