package app.community.model;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private long postId;
    private String userNickname;
}