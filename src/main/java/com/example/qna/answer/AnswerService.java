package com.example.qna.answer;

import com.example.qna.question.Question;
import com.example.qna.question.QuestionRepository;
import com.example.qna.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void save(Long questionId, Answer answer, User user) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("댓글 작성 실패 : 질문 아이디를 찾을 수 없습니다."));
        answer.setUser(user);
        answer.setQuestion(question);
        answerRepository.save(answer);
    }

    @Transactional
    public void delete(Long answerId) {
        answerRepository.deleteById(answerId);
    }

}
