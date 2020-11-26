package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;
}
