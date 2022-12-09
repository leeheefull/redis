package com.leeheeefull.redis.log;

import com.leeheeefull.redis.log.application.LogService;
import com.leeheeefull.redis.log.domain.LogContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @BeforeEach
    public void setUp() {
        logService.deleteAllLog();
    }

    @Test
    public void StringLogTest() {
        // given
        var expected = 10;

        // when
        logService.createStringLog(expected);
        var actual = logService.getStringLog(getNow());

        // then
        assertThat(actual).isEqualTo(expected + "");
    }

    @Test
    public void HashLogTest() {
        // given
        var hashKey = "1";
        var expected = LogContent.builder()
                .title("alphabet")
                .content("abcdefghijklmnopqrstuvwxyz")
                .build();

        // when
        logService.createHashLog(hashKey, expected);
        var actual = logService.getHashLog(getNow(), hashKey);

        assertThat(expected.getTitle()).isEqualTo(actual.getTitle());
    }

    private String getNow() {
        return LocalDate.now()
                .toString()
                .replace("-", "");
    }

}
