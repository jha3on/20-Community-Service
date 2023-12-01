package app.community.mapper;

import app.community.model.ChatRoom;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ChatRoomMapper {
    void createChatRoom(ChatRoom chatRoom);

    int countChatRoomByName(String name);
    int countChatRoomByUserNickname(long id, String userNickname);
    ChatRoom selectChatRoomById(long id);
    List<ChatRoom> selectAllChatRoom();

    void updateChatRoom(ChatRoom chatRoom);
    int deleteChatRoom(long id, String userNickname);
}