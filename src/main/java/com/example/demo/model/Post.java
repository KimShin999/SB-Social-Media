package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private PrivacyPost privacyPost;

    @OneToMany
    private List<Comment> comments;

    @OneToMany
    private List<AppLike> likes;

    @OneToMany
    private List<PostImage> images;

    private Timestamp createAt;

    private String notification;

}
