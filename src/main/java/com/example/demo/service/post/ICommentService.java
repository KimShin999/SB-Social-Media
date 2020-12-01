package com.example.demo.service.post;

import com.example.demo.model.AppUser;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Iterable<Comment> findAll();
    Comment save (Comment comment);
    Comment remove (Long id);
    Optional<Comment> findById(Long id);
    Iterable<Comment> findAllByPost(Post post);
}
