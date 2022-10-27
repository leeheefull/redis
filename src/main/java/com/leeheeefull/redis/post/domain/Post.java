package com.leeheeefull.redis.post.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "post", timeToLive = 30)
public class Post {

    @Id
    private String id;

    private String title;

    private String content;

    private LocalDateTime createAt;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createAt = LocalDateTime.now();
    }

}
