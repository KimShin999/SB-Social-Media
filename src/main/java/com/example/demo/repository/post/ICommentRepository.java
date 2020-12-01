package com.example.demo.repository.post;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentRepository extends PagingAndSortingRepository<Comment, Long> {
    Iterable<Comment> findAllByPost(Post post);
}
