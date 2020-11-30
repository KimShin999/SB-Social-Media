package com.example.demo.repository.gender;

import com.example.demo.model.Gender;
import org.springframework.data.repository.CrudRepository;

public interface IGenderRepository extends CrudRepository<Gender,Long> {
}
