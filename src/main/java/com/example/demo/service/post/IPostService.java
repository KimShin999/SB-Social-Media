package com.example.demo.service.post;
import com.example.demo.model.AppUser;
import com.example.demo.model.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    Iterable<Post> findAll();
    Post save (Post post);
    void remove (Long id);
    Optional<Post> findById(Long id);
    List<Post> findAllByAppUser(AppUser user);

}
