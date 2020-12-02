package com.example.demo.service.post;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.post.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment remove(Long id) {
        Comment comment =commentRepository.findById(id).get();
        commentRepository.deleteById(id);
        return comment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Iterable<Comment> findAllByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }
}
