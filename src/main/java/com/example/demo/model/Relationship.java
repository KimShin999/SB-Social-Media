package com.example.demo.model;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private AppUser firstUser;

    @ManyToOne
    private AppUser secondUser;

    @ManyToOne
    private Status status;

    private String notification;
}
