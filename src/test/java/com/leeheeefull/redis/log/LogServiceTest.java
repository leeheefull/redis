package com.leeheeefull.redis.log;

import com.leeheeefull.redis.log.application.LogService;
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

    private String getNow() {
        return LocalDate.now()
                .toString()
                .replace("-", "");
    }

}
