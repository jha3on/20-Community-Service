package app.community.model;

import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private long postId;
    private String userNickname;

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "댓글 형식이 맞지 않습니다.")
    private String content;

    private int likeCount;
    private Date createdAt;
    private Date updatedAt;
}