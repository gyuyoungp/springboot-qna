package com.example.qna.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;


    @Lob
    private String contents;

    private LocalDateTime createDate;

    public Answer(User writer, Question question, String contents) {
        this.writer = writer;
        this.contents = contents;
        this.question = question;
        this.createDate = LocalDateTime.now();
    }


}
