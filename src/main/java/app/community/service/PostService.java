package app.community.service;

import app.community.configuration.constatnt.Variables;
import app.community.mapper.PostMapper;
import app.community.model.Criteria;
import app.community.model.Post;
import app.community.model.PostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public void createPost(Post post) {
        postMapper.createPost(post);
    }

    public int countAllPost() {
        return postMapper.countAllPost();
    }

    public int countAllSearchedPost(String option, String keyword) {
        return postMapper.countAllSearchedPost(option, keyword);
    }

    public int countPostByNickname(long id, String userNickname) {
        return postMapper.countPostByNickname(id, userNickname);
    }

    public int countPostLikeByNickname(long postId, String userNickname) {
        return postMapper.countPostLikeByNickname(postId, userNickname);
    }

    public List<Post> selectAllPost(Criteria criteria) {
        return postMapper.selectAllPost(criteria);
    }

    public List<Post> selectAllSearchedPost(Criteria criteria, String option, String keyword) {
        return postMapper.selectAllSearchedPost(criteria, option, keyword);
    }

    public Post selectPostById(long id) {
        return postMapper.selectPostById(id);
    }

    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    public void updatePostByViewCount(long id) {
        postMapper.updatePostByViewCount(id);
    }

    public void updatePostByCommentCount(long id, Variables.Command command) {
        postMapper.updatePostByCommentCount(id, command);
    }

    public int updatePostByLikeCount(long postId, String userNickname) {
        int result = this.countPostLikeByNickname(postId, userNickname);

        if (result == 0) {
            PostLike postLike = PostLike.builder()
                    .postId(postId)
                    .userNickname(userNickname)
                    .build();
            this.postMapper.createPostLike(postLike);
            this.postMapper.updatePostByLikeCount(postId, Variables.Command.UP_COMMAND);

        } else if (result == 1) {
            this.postMapper.deletePostLike(postId, userNickname);
            this.postMapper.updatePostByLikeCount(postId, Variables.Command.DOWN_COMMAND);
        }

        return result;
    }

    public void deletePost(long id, String userNickname) {
        postMapper.deletePost(id, userNickname);
    }

    public void deletePostLike(long postId, String userNickname) {
        postMapper.deletePost(postId, userNickname);
    }
}