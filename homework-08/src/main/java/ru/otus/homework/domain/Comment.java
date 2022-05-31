package ru.otus.homework.domain;

import lombok.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Comment {
    private String id;
    private String nickname;
    private String content;
    private Instant creationTimestamp;
}
