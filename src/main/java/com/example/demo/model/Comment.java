package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Post post;

    private String content;

    private String img;

    private String notification;

    private Timestamp createAt;

}
