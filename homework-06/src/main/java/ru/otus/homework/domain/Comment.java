package ru.otus.homework.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "content", nullable = false)
    private String content;
    @CreatedDate
    @Column(name = "creation_timestamp", nullable = false)
    private Instant creationTimestamp;
    @ManyToOne(targetEntity = Book.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;
}
