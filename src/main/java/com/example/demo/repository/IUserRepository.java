package com.example.demo.repository;

import com.example.demo.model.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserRepository extends PagingAndSortingRepository<AppUser,Long> {
}
