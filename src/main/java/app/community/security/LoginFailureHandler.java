package app.community.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.community.configuration.constatnt.Variables.FAILURE_MESSAGE;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String email = request.getParameter("email");
        // String password = request.getParameter("password");
        String failureMessage;

        if (e instanceof BadCredentialsException) {
            failureMessage = "이메일 또는 비밀번호를 확인해주세요.";
        } else if (e instanceof UsernameNotFoundException) {
            failureMessage = "이메일 또는 비밀번호를 확인해주세요.";
        } else if (e instanceof InternalAuthenticationServiceException) {
            failureMessage = "이메일 또는 비밀번호를 확인해주세요.";
        } else if (e instanceof AuthenticationCredentialsNotFoundException) {
            failureMessage = "가입 인증이 되지 않은 계정입니다.";
        } else {
            failureMessage = "로그인 시, 문제가 발생하였습니다.";
        }

        request.setAttribute("email", email);
        request.setAttribute(FAILURE_MESSAGE, failureMessage);
        // 로그인 실패 시, 메뉴의 로그인 버튼이 없어지는 문제로 추가하였다. (header.jsp)
        request.setAttribute("isLoginFailure", true);
        request.getRequestDispatcher("/user/login").forward(request, response);
    }
}