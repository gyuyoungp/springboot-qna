package com.example.qna.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String userId;

    private String password;
    private String username;
    private String email;

    public void update(User updateUser) {
        this.password = updateUser.password;
        this.username = updateUser.username;
        this.email = updateUser.email;
    }
}
