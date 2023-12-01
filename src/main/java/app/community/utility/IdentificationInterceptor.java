package app.community.utility;

import app.community.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import static app.community.utility.Identification.Type.*;
import static app.community.utility.ScriptUtils.alertAndRedirectScript;
import static app.community.utility.SecurityUtils.getAuthenticatedUser;

@Slf4j
@RequiredArgsConstructor
public class IdentificationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(IdentificationInterceptor.class);
    private final PostService postService;
    private final CommentService commentService;
    private final ChatRoomService chatRoomService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (checkType(handler) == null) {
            return true;

        } else {
            Identification idType = checkType(handler);
            String nickname = Optional.ofNullable(getAuthenticatedUser()
                    .getNickname())
                    .orElse("익명");

            switch (idType.type()) {
                case USER:
                    logger.info("ID Type(USER)");
                    return true;
                case POST:
                case COMMENT:
                case CHAT_ROOM:
                    logger.info("ID Type(POST/COMMENT/CHAT_ROOM)");

                    Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

                    long id = (long) pathVariables.get("id");
                    int result = 0;
                    String redirectMessage = "";
                    String redirectUri = "";

                    if (idType.type().equals(POST)) {
                        result = postService.countPostByNickname(id, nickname);
                        redirectMessage = "해당 게시글에 대한 수정/삭제 권한이 없습니다.";
                        redirectUri = "/board/post/" + id;

                    } else if (idType.type().equals(COMMENT)) {
                        result = commentService.countCommentByNickname(id, nickname);
                        long redirectPostId = commentService.selectCommentById(id).getPostId();
                        redirectMessage = "해당 댓글에 대한 수정/삭제 권한이 없습니다.";
                        redirectUri = "/board/post/" + redirectPostId;

                    } else if (idType.type().equals(CHAT_ROOM)) {
                        result = chatRoomService.countChatRoomByUserNickname(id, nickname);
                        redirectMessage = "해당 채팅방에 대한 수정/삭제 권한이 없습니다.";
                        redirectUri = "/chat/room/list";
                    }

                    if (result == 1) {
                        return true;

                    } else {
                        alertAndRedirectScript(response, redirectMessage, redirectUri);
                        return false;
                    }

                default:
                    return false;
            }
        }
    }

    private Identification checkType(Object handler) {
        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getMethodAnnotation(Identification.class);

        } else {
            return null;
        }
    }
}