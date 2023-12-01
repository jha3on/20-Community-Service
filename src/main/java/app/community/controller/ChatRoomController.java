package app.community.controller;

import app.community.model.ChatRoom;
import app.community.security.CustomUserDetails;
import app.community.service.ChatRoomService;
import app.community.utility.Identification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import static app.community.configuration.constatnt.Variables.FAILURE_MESSAGE;
import static app.community.configuration.constatnt.Variables.SUCCESS_MESSAGE;
import static app.community.utility.SecurityUtils.getAuthenticatedUser;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat/*")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/room/new")
    public String getChatRoomCreationPage(Model model) {
        log.info("getChatRoomCreationPage()");

        model.addAttribute("chatRoom", new ChatRoom());

        return "/chat/room/new";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/room/new")
    public String doChatRoomCreation(@ModelAttribute("chatRoom") @Valid ChatRoom chatRoom, BindingResult result, Model model, RedirectAttributes attributes) {
        log.info("doChatRoomCreation()");

        if (result.hasErrors() || chatRoomService.countChatRoomByName(chatRoom.getName()) != 0) {
            model.addAttribute(FAILURE_MESSAGE, "채팅방 정보를 확인해주세요.");
            return "/chat/room/new";

        } else {
            chatRoom.setUserNickname(getAuthenticatedUser().getNickname());
            chatRoomService.createChatRoom(chatRoom);

            attributes.addFlashAttribute(SUCCESS_MESSAGE, "채팅방이 등록되었습니다.");
            return "redirect:/chat/room/list";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/room/list")
    public String getChatRoomListPage(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("getChatRoomListPage()");

        model.addAttribute("roomList", chatRoomService.selectAllChatRoom());
        model.addAttribute("userNickname", customUserDetails.getNickname());

        return "/chat/room/list";
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/room/{id}")
    public Map<String, Object> getChatRoom(@PathVariable long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("getChatRoom()");

        Map<String, Object> map = new HashMap<>();
        ChatRoom room = chatRoomService.selectChatRoomById(id);
        map.put("room", room);
        map.put("userNickname", customUserDetails.getNickname());

        return map;
    }

    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.CHAT_ROOM)
    @GetMapping("/room/{id}/edit")
    public String getChatRoomEditPage(@PathVariable("id") long id, Model model) {
        log.info("getChatRoomEditPage()");

        model.addAttribute("chatRoom", chatRoomService.selectChatRoomById(id));

        return "/chat/room/edit";
    }

    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.CHAT_ROOM)
    @PostMapping("/room/{id}/edit")
    public String doChatRoomEdit(@PathVariable("id") long id, @ModelAttribute("chatRoom") @Valid ChatRoom room, BindingResult result, Model model, RedirectAttributes attributes) {
        log.info("roomEditPost()");

        if (result.hasErrors()) {
            model.addAttribute(FAILURE_MESSAGE, "채팅방 정보를 확인해주세요.");
            return "/chat/room/edit";

        } else {
            room.setUserNickname(getAuthenticatedUser().getNickname());
            chatRoomService.updateChatRoom(room);

            attributes.addFlashAttribute(SUCCESS_MESSAGE, "채팅방이 수정되었습니다.");
            return "redirect:/chat/room/list";
        }
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.CHAT_ROOM)
    @PostMapping("/room/{id}/delete")
    public int doChatRoomDelete(@PathVariable("id") long id) {
        log.info("doChatRoomDelete()");

        return chatRoomService.deleteChatRoom(id, getAuthenticatedUser().getNickname());
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/room/{id}/check")
    public int doChatRoomCheck(@PathVariable("id") long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("doChatRoomCheck()");

        return (chatRoomService.countChatRoomByUserNickname(id, customUserDetails.getNickname()) == 1) ? 1 : 0;
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/room/name/check")
    public int doChatRoomNameCheck(@RequestBody ChatRoom chatRoom) {
        log.info("doChatRoomNameCheck()");

        return chatRoomService.countChatRoomByName(chatRoom.getName());
    }
}