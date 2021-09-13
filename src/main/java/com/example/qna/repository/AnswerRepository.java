package com.example.qna.repository;

import com.example.qna.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
