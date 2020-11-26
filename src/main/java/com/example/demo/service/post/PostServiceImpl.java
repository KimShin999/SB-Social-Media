package com.example.demo.service.post;

import com.example.demo.model.Post;
import com.example.demo.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private IPostRepository postRepository;
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post remove(Long id) {
        postRepository.deleteById(id);
        return postRepository.findById(id).get();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
}
