package app.community.model;

import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private long roomId;
    private String type;
    private String userNickname;

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "채팅 메시지는 1~100자 이내로 작성해야 합니다.")
    private String content;
}