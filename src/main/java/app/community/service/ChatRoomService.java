package app.community.service;

import app.community.mapper.ChatRoomMapper;
import app.community.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomMapper chatRoomMapper;

    public void createChatRoom(ChatRoom chatRoom) {
        chatRoomMapper.createChatRoom(chatRoom);
    }

    public int countChatRoomByName(String name) {
        return chatRoomMapper.countChatRoomByName(name);
    }

    public int countChatRoomByUserNickname(long id, String userNickname) {
        return chatRoomMapper.countChatRoomByUserNickname(id, userNickname);
    }

    public ChatRoom selectChatRoomById(long id) {
        return chatRoomMapper.selectChatRoomById(id);
    }

    public List<ChatRoom> selectAllChatRoom() {
        return chatRoomMapper.selectAllChatRoom();
    }

    public void updateChatRoom(ChatRoom chatRoom) {
        chatRoomMapper.updateChatRoom(chatRoom);
    }

    public int deleteChatRoom(long id, String userNickname) {
        return chatRoomMapper.deleteChatRoom(id, userNickname);
    }
}