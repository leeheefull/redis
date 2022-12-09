package com.leeheeefull.redis.log.application;

import com.leeheeefull.redis.log.infrastructure.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.leeheeefull.redis.util.RedisKeyConstants.DEFAULT_KEY_PREFIX;
import static com.leeheeefull.redis.util.RedisKeyConstants.STRING_LOG_SUFFIX;

@RequiredArgsConstructor
@Service
public class LogService {

    private final LogRepository logRepository;

    public void deleteAllLog() {
        logRepository.deleteAll();
    }

    public void createStringLog(int count) {
        logRepository.createStringValue(STRING_LOG_SUFFIX, count);
    }

    public String getStringLog(String time) {
        return logRepository.getStringValue(DEFAULT_KEY_PREFIX + time + STRING_LOG_SUFFIX);
    }

}
