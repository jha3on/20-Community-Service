package app.community.model;

import app.community.model.enums.UserRoleType;
import app.community.model.enums.UserStatus;
import lombok.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호 형식이 맞지 않습니다.")
    private String password;

    @Pattern(regexp = "^[가-힣]{2,5}$", message = "이름 형식이 맞지 않습니다.")
    private String name;

    @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$", message = "핸드폰 번호 형식이 맞지 않습니다.")
    private String phone;

    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임 형식이 맞지 않습니다.")
    private String nickname;

    private UserRoleType userRoleType;
    private UserStatus userStatus;

    private Date createdAt;
    private Date updatedAt;
}