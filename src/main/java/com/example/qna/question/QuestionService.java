package com.example.qna.question;

import com.example.qna.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public void save(Question question, User user) {
        question.setUser(user);
        questionRepository.save(question);
    }

    @Transactional(readOnly = true)
    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다."));
    }

    @Transactional
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Question updateQuestion) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.")); // 영속화 완료
        question.setTitle(updateQuestion.getTitle());
        question.setContent(updateQuestion.getContent());
        // 해당 함수 종료시(Service가 종료될 때) 트랜잭션이 종료된다. 이때 더티체킹 - 자동 업데이트 flush
    }
}
