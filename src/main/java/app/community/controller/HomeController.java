package app.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @PreAuthorize("permitAll()")
    @GetMapping("/")
    public String getHomePage() {
        log.info("getHomePage()");

        return "/home";
    }
}