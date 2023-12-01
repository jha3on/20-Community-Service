package app.community.model;

import app.community.model.enums.PostType;
import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String userNickname;
    private PostType postType;

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "게시글 제목 형식이 맞지 않습니다.")
    private String title;

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,500}$", message = "게시글 내용 형식이 맞지 않습니다.")
    private String content;

    private int commentCount;
    private int viewCount;
    private int likeCount;
    private Date createdAt;
    private Date updatedAt;
}