package org.lievasoft.instructor.entity;

import jakarta.persistence.*;
import org.lievasoft.instructor.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;
    private String password;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Account() {
    }

    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public Role getRole() {
        return this.role;
    }

    public UserDetails toUserDetails() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .roles(this.role.name())
                .build();
    }
}
