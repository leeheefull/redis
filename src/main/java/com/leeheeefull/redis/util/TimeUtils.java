package com.leeheeefull.redis.util;

import java.time.LocalDate;

public class TimeUtils {

    public static String getNow() {
        return LocalDate.now()
                .toString()
                .replace("-", "");
    }

}
