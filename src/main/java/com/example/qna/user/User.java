package com.example.qna.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String email;

    //    @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, USER

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;

    public void update(User updateUser) {
        this.password = updateUser.password;
        this.nickname = updateUser.nickname;
        this.email = updateUser.email;
    }
}
