package com.leeheeefull.redis.post.repository;

import com.leeheeefull.redis.post.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, String> {
}
