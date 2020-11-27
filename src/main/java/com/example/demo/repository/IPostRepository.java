package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostRepository extends PagingAndSortingRepository<Post,Long> {
}
