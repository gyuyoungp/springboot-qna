package com.example.qna.answer;

import com.example.qna.question.Question;
import com.example.qna.user.User;
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
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @Lob
    private String content;

    @CreationTimestamp
    private Timestamp createDate;

    public Answer(User user, Question question, String content) {
        this.user = user;
        this.content = content;
        this.question = question;
    }

}
