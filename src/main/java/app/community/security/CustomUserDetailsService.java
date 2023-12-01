package app.community.security;

import app.community.mapper.UserMapper;
import app.community.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.selectUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        // 로그인 날짜 수정
        userMapper.updateUserByUpdatedAt(email);

        return CustomUserDetails.of(user);
    }
}