package com.example.demo.service.postImg;
import com.example.demo.model.PostImage;
import java.util.Optional;

public interface IPostImgService {
    Iterable<PostImage> findAll();
    PostImage save (PostImage post);
    PostImage remove (Long id);
    Optional<PostImage> findById(Long id);
}
