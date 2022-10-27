package com.leeheeefull.redis.post.repository;

import com.leeheeefull.redis.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    private static final String TITLE_SAMPLE = "Java";
    private static final String CONTENT_SAMPLE = "거참 너무 재밌네";

    @Test
    public void 레디스가_어디_한_번_잘_되나_볼까() {
        var post = new Post(TITLE_SAMPLE, CONTENT_SAMPLE);
        postRepository.save(post);

        // "keyspace:id" -> "post:id" 값을 가져옴
        var actual = postRepository.findById(post.getId());

        assertEquals(TITLE_SAMPLE, actual.get().getTitle());
    }

}
