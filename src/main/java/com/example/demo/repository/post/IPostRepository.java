package com.example.demo.repository.post;

import com.example.demo.model.AppUser;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPostRepository extends PagingAndSortingRepository<Post,Long> {
    List<Post> findAllByAppUser(AppUser user);
}
