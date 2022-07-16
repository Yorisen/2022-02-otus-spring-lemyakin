package ru.otus.homework.domain.nosql;

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
