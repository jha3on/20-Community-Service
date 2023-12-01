package app.community.controller;

import app.community.model.ChatMessage;
import app.community.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/room/{id}")
    public void chatRoomMessage(@DestinationVariable("id") String id, @Payload ChatMessage chatMessage) {
        log.info("chatRoomMessage()");

        chatMessageService.sendChatMessage(chatMessage);
    }
}