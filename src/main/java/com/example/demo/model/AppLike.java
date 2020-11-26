package com.example.demo.model;
import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class AppLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Post post;

    private String notification;
    private Timestamp createAt;
}
