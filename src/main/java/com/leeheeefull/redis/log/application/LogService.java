package com.leeheeefull.redis.log.application;

import com.leeheeefull.redis.log.domain.LogContent;
import com.leeheeefull.redis.log.infrastructure.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.leeheeefull.redis.util.RedisKeyConstants.*;

@RequiredArgsConstructor
@Service
public class LogService {

    private final LogRepository logRepository;

    public void deleteAllLog() {
        logRepository.deleteAll();
    }

    public void createStringLog(int count) {
        logRepository.createStringValue(STRING_LOG_KEY_SUFFIX, count);
    }

    public String getStringLog(String time) {
        return logRepository.getStringValue(DEFAULT_KEY_PREFIX + time + STRING_LOG_KEY_SUFFIX);
    }

    public void createHashLog(String hashKey, LogContent hashValue) {
        logRepository.createHashValue(HASH_LOG_KEY_SUFFIX, hashKey, hashValue);
    }

    public LogContent getHashLog(String time, String hashKey) {
        return logRepository.getHashValue(DEFAULT_KEY_PREFIX + time + HASH_LOG_KEY_SUFFIX, hashKey);
    }

}
