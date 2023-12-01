package app.community.mapper;

import app.community.model.User;
import app.community.model.enums.UserRoleType;
import app.community.model.enums.UserStatus;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    void createUser(User user);

    int countUserByEmail(String email);
    int countUserByPhone(String phone);
    int countUserByNickname(String nickname);
    int countAllUser(UserStatus userStatus);
    User selectUserByEmail(String email);
    User selectUserByUser(User user, UserRoleType userRoleType);
    List<User> selectAllUser(UserStatus userStatus);

    int updateUserByEmail(User user);
    void updateUserByPassword(User user);
    int updateUserByName(User user);
    int updateUserByPhone(User user);
    int updateUserByNickName(User user);
    int updateUserByUserRoleType(User user, UserRoleType userRoleType);
    int updateUserByUserStatus(User user, UserStatus userStatus);
    void updateUserByUpdatedAt(String email);
    void updateUser(User user);
}