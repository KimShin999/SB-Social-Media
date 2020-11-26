package com.example.demo.repository;
import com.example.demo.model.PostImage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostImgRepository extends PagingAndSortingRepository<PostImage,Long> {
}
