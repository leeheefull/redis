package com.leeheeefull.redis.util;

public class RedisKeyUtils {

    private static final String DEFAULT_KEY_PREFIX = "redis:leeheefull:";
    public static final String STRING_LOG_KEY_SUFFIX = ":string:log";
    public static final String HASH_LOG_KEY_SUFFIX = ":hash:log";
    public static final long DEFAULT_EXPIRE_DAYS = 2;

    public static String generateKey(String time, String keySuffix) {
        return DEFAULT_KEY_PREFIX + time + keySuffix;
    }

}