package app.community.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    @Pattern(regexp = "^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,100}$", message = "채팅방 이름 형식이 맞지 않습니다.")
    private String name;

    private String userNickname;

    @Range(min = 2, max = 10, message = "채팅방 인원은 2~10명까지 가능합니다.")
    private int maximum;

    private Date createdAt;
    private Date updatedAt;
}