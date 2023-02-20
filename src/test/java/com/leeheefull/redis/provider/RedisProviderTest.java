package com.leeheefull.redis.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisProviderTest {
    @Autowired
    private RedisProvider redisProvider;

    @Test
    public void saveAndFindTest() {
        // given
        String key = "test-key";
        String value = "test-value";
        redisProvider.save(key, value);

        // when
        var actual = redisProvider.find("test-key", String.class)
                .get();

        // then
        assertThat(actual).isEqualTo(value);

        // rollback
        redisProvider.remove(key);
    }
}
