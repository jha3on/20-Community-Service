package app.community.service;

import app.community.mapper.UserMapper;
import app.community.model.User;
import app.community.model.enums.UserRoleType;
import app.community.model.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        userMapper.createUser(user);
    }

    public boolean existsByUser(User user) {
        if (countUserByEmail(user.getEmail()) != 0) return true;
        if (countUserByPhone(user.getPhone()) != 0) return true;

        return countUserByNickname(user.getNickname()) != 0;
    }

    public int countUserByEmail(String email) {
        return userMapper.countUserByEmail(email);
    }

    public int countUserByPhone(String phone) {
        return userMapper.countUserByPhone(phone);
    }

    public int countUserByNickname(String nickname) {
        return userMapper.countUserByNickname(nickname);
    }

    public User selectUserByUser(User user, UserRoleType userRoleType) {
        return userMapper.selectUserByUser(user, userRoleType);
    }

    public void updateUserByPassword(User user) {
        userMapper.updateUserByPassword(user);
    }

    public int updateUserStatus(User user, UserStatus userStatus) {
        return userMapper.updateUserByUserStatus(user, userStatus);
    }

    public void updateUserByUpdatedAt(String email) {
        userMapper.updateUserByUpdatedAt(email);
    }

    public void updateUser(User user) {
        System.out.println(user.getId());
        System.out.println(user.getEmail());
        System.out.println(user.getPhone());
        System.out.println(user.getNickname());
        userMapper.updateUser(user);
    }
}