package com.example.demo.service.post;
import com.example.demo.model.Post;
import java.util.Optional;

public interface IPostService {
    Iterable<Post> findAll();
    Post save (Post post);
    Post remove (Long id);
    Optional<Post> findById(Long id);

}
