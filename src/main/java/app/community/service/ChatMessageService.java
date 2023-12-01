package app.community.service;

import app.community.model.ChatMessage;
import app.community.model.enums.ChatMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final SimpMessageSendingOperations messagingTemplate;

    public void sendChatMessage(ChatMessage chatMessage) {
        if (ChatMessageType.JOIN.getValue().equals(chatMessage.getType())) {
            chatMessage.setContent(chatMessage.getUserNickname() + "님이 들어왔습니다.");
            chatMessage.setUserNickname("[알림]");

        } else if (ChatMessageType.QUIT.getValue().equals(chatMessage.getType())) {
            chatMessage.setContent(chatMessage.getUserNickname() + "님이 나갔습니다.");
            chatMessage.setUserNickname("[알림]");
        }

        messagingTemplate.convertAndSend("/topic/chat/room/" + chatMessage.getRoomId(), chatMessage);
    }
}