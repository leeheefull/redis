package com.leeheeefull.redis.log.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class LogContent implements Serializable {

    private String title;
    private String content;

    @Builder
    private LogContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
