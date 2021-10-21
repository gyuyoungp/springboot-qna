package com.example.qna.question;

import com.example.qna.answer.Answer;
import com.example.qna.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Question = Many User = One : 한명의 유저는 여러개의 질문을 작성할 수 있다. (fetch = FetchType.EAGER 기본 패치 전략) 무조건 들고와
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    // mappedBy 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 컬럼을 만들지 마세요., question 는 필드 이름, (fetch = FetchType.LAZY 기본 패치 전략)
    @OrderBy("id desc")
    @JsonIgnoreProperties({"question"})
    private List<Answer> answers;

    @CreationTimestamp
    private Timestamp createDate;

    public Question(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
