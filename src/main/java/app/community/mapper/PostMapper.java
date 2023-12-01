package app.community.mapper;

import app.community.configuration.constatnt.Variables;
import app.community.model.Criteria;
import app.community.model.Post;
import app.community.model.PostLike;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PostMapper {
    void createPost(Post post);
    void createPostLike(PostLike postLike);

    int countAllPost();
    int countAllSearchedPost(String option, String keyword);
    int countPostByNickname(long id, String userNickname);
    int countPostLikeByNickname(long postId, String userNickname);
    List<Post> selectAllPost(Criteria criteria);
    List<Post> selectAllSearchedPost(Criteria criteria, String option, String keyword);
    Post selectPostById(long id);

    void updatePost(Post post);
    void updatePostByViewCount(long id);
    void updatePostByCommentCount(long id, Variables.Command command);
    void updatePostByLikeCount(long id, Variables.Command command);

    void deletePost(long id, String userNickname);
    void deletePostLike(long postId, String userNickname);
}