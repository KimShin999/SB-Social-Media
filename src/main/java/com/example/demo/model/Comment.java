package com.example.demo.model;
import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Post post;

    private String content;

    private String img;

    private String notification;

    private Timestamp createAt;

}
