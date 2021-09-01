package com.example.qna.repository;

import com.example.qna.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 어떤 클래스, 기본키가 어떤 타입
public interface UserRepository extends JpaRepository<User, Long> {

}
