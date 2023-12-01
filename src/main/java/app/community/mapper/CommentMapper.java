package app.community.mapper;

import app.community.configuration.constatnt.Variables;
import app.community.model.Comment;
import app.community.model.CommentLike;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {
    void createComment(Comment comment);
    void createCommentLike(CommentLike commentLike);

    int countAllComment();
    int countCommentById(long postId);
    int countCommentByNickname(long id, String userNickname);
    int countCommentLikeByNickname(long postId, long commentId, String userNickname);
    Comment selectCommentById(long id);
    List<Comment> selectAllComment(long postId);
    List<CommentLike> selectAllCommentLike(long postId, String userNickname);

    int updateComment(Comment comment);
    void updateCommentLike(long postId, long id, Variables.Command command);

    int deleteComment(long id, String userNickname);
    void deleteCommentLike(long postId, long commentId, String userNickname);
}