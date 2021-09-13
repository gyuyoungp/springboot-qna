package com.example.qna.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private String title;

    @Lob
    private String contents;

    //    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question")
//    @OrderBy("id ASC")
    private List<Answer> answers;

    public Question(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
