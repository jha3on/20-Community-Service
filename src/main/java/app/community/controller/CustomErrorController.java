package app.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@PreAuthorize("permitAll()")
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String getErrorPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String statusCode = String.valueOf(status);
        String errorMessage = "";

        switch (statusCode) {
            case "400":
                errorMessage = "잘못된 요청입니다.";
                break;
            case "403":
                errorMessage = "접근 권한이 없습니다.";
                break;
            case "404":
                errorMessage = "요청한 페이지를 찾을 수 없습니다.";
                break;
            case "405":
                errorMessage = "요청된 메소드가 허용되지 않습니다.";
                break;
            case "503":
                errorMessage = "서비스를 이용할 수 없습니다.";
                break;
            default:
                errorMessage = "서버 오류가 발생하였습니다.";
                break;
        }

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);

        return "/error/error";
    }
}