package com.example.demo.model;
import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @NotBlank
    @Size(max = 20)
    private String username;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppRoLe> roles = new HashSet<>();

    private String firstName;
    private String lastName;
    private String avatar;
    private String cover;
    private String phoneNumber;
    private Date dateOfBirth;

    @ManyToOne
    private Gender gender;

    @Email
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @OneToMany
    private List<Post> Posts;
}
