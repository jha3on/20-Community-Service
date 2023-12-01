package app.community.controller;

import app.community.model.User;
import app.community.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import static app.community.configuration.constatnt.Variables.FAILURE_MESSAGE;
import static app.community.configuration.constatnt.Variables.SUCCESS_MESSAGE;
import static app.community.utility.SecurityUtils.convertUserDetailsToUser;
import static app.community.utility.SecurityUtils.getAuthenticatedUser;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserController {
    private final UserService userService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/consent")
    public String getConsentPage() {
        log.info("getConsentPage()");

        return "/user/consent";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/join")
    public String getJoinPage(Model model) {
        log.info("getJoinPage()");

        model.addAttribute("user", new User());

        return "/user/join";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/join")
    public String doJoin(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        log.info("doJoin()");

        if (result.hasErrors() || userService.existsByUser(user)) {
            model.addAttribute(FAILURE_MESSAGE, "계정 등록 정보를 확인해주세요.");
            return "/user/join";

        } else if (user.getNickname().equals("익명")) {
            model.addAttribute(FAILURE_MESSAGE, "해당 닉네임은 사용할 수 없습니다.");
            return "/user/join";

        } else {
            userService.createUser(user);
            return "redirect:/user/complete";
        }
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/complete")
    public String getJoinCompletionPage() {
        log.info("getJoinCompletionPage()");

        return "/user/complete";
    }

    @RequestMapping("/login")
    public String doLogin() {
        log.info("doLogin()");

        return "/user/login";
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @PostMapping("/email/check")
    public int doEmailCheck(@RequestBody User user) {
        log.info("doEmailCheck()");

        return userService.countUserByEmail(user.getEmail());
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @PostMapping("/phone/check")
    public int doPhoneCheck(@RequestBody User user) {
        log.info("doPhoneCheck()");

        return userService.countUserByPhone(user.getPhone());
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @PostMapping("/nickname/check")
    public int doNicknameCheck(@RequestBody User user) {
        log.info("doNicknameCheck()");

        return userService.countUserByNickname(user.getNickname());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        log.info("getProfilePage()");

        User user = convertUserDetailsToUser(getAuthenticatedUser());
        model.addAttribute("user", user);

        return "/user/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit")
    public String getEditPage(Model model) {
        log.info("getEditPage()");

        User user = convertUserDetailsToUser(getAuthenticatedUser());
        model.addAttribute("user", user);

        return "/user/edit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = {"/edit/email", "/edit/name", "/edit/phone", "/edit/nickname"})
    public String doEdit(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, RedirectAttributes attributes) throws Exception {
        log.info("doEdit()");
        System.out.println(user.getId());
        System.out.println(user.getEmail());
        System.out.println(user.getPhone());
        System.out.println(user.getNickname());

        if (result.hasErrors() || userService.existsByUser(user)) {
            model.addAttribute(FAILURE_MESSAGE, "계정 정보를 확인해주세요.");
            return "/user/edit";

        } else {
            user.setId(getAuthenticatedUser().getId());
            userService.updateUser(user);

            attributes.addFlashAttribute(SUCCESS_MESSAGE, "계정 정보가 수정되었습니다.");
            return "redirect:/user/edit";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/password")
    public String doPasswordEdit(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, RedirectAttributes attributes) {
        log.info("doPasswordEdit()");

        if (result.hasErrors()) {
            model.addAttribute(FAILURE_MESSAGE, "비밀번호를 확인해주세요.");
            return "/user/edit";

        } else {
            user.setEmail(getAuthenticatedUser().getEmail());
            userService.updateUserByPassword(user);

            attributes.addFlashAttribute(SUCCESS_MESSAGE, "비밀번호가 수정되었습니다.");
            return "redirect:/user/edit";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/delete")
    public String doUserDelete() {
        // ...
        return "redirect:/";
    }
}