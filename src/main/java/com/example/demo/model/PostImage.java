package com.example.demo.model;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String img;
}
