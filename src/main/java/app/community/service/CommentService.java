package app.community.service;

import app.community.configuration.constatnt.Variables;
import app.community.mapper.CommentMapper;
import app.community.model.Comment;
import app.community.model.CommentLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public void createComment(Comment comment) {
        commentMapper.createComment(comment);
    }

    public int countAllComment() {
        return commentMapper.countAllComment();
    }

    public int countCommentById(long postId) {
        return commentMapper.countCommentById(postId);
    }

    public int countCommentByNickname(long id, String userNickname) {
        return commentMapper.countCommentByNickname(id, userNickname);
    }

    public int countCommentLikeByNickname(long postId, long commentId, String userNickname) {
        return commentMapper.countCommentLikeByNickname(postId, commentId, userNickname);
    }

    public Comment selectCommentById(long id) {
        return commentMapper.selectCommentById(id);
    }

    public List<Comment> selectAllComment(long postId) {
        return commentMapper.selectAllComment(postId);
    }

    public List<CommentLike> selectAllCommentLike(long postId, String userNickname) {
        return commentMapper.selectAllCommentLike(postId, userNickname);
    }

    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    public int updateCommentLike(long postId, long commentId, String userNickname) {
        int result = this.countCommentLikeByNickname(postId, commentId, userNickname);

        if (result == 0) {
            CommentLike commentLike = CommentLike.builder()
                    .postId(postId)
                    .commentId(commentId)
                    .userNickname(userNickname)
                    .build();
            this.commentMapper.createCommentLike(commentLike);
            this.commentMapper.updateCommentLike(postId, commentId, Variables.Command.UP_COMMAND);

        } else if (result == 1) {
            this.commentMapper.deleteCommentLike(postId, commentId, userNickname);
            this.commentMapper.updateCommentLike(postId, commentId, Variables.Command.DOWN_COMMAND);
        }

        return result;
    }

    public int deleteComment(long id, String userNickname) {
        return commentMapper.deleteComment(id, userNickname);
    }
}