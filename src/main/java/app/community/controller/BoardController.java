package app.community.controller;

import app.community.configuration.constatnt.Variables;
import app.community.model.Comment;
import app.community.model.Criteria;
import app.community.model.Post;
import app.community.security.CustomUserDetails;
import app.community.service.CommentService;
import app.community.service.PageMaker;
import app.community.service.PostService;
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
import java.io.IOException;
import java.util.*;
import static app.community.configuration.constatnt.Variables.FAILURE_MESSAGE;
import static app.community.configuration.constatnt.Variables.SUCCESS_MESSAGE;
import static app.community.utility.SecurityUtils.getAuthenticatedUser;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
    private final PostService postService;
    private final CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/new")
    public String postNewGet(Model model) {
        log.info("postNewGet()");

        model.addAttribute("post", new Post());

        return "/board/post/new";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/new")
    public String postNewPost(@ModelAttribute("post") @Valid Post post, BindingResult result, Model model) {
        log.info("postNewPost()");

        if (result.hasErrors()) {
            model.addAttribute(FAILURE_MESSAGE, "게시글 제목 또는 내용을 확인해주세요.");

            return "/board/post/new";

        } else {
            post.setUserNickname(getAuthenticatedUser().getNickname());
            postService.createPost(post);

            return "redirect:/board/post/list";
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/post/list")
    public String postListGet(Criteria criteria, Model model, @RequestParam(value = "option", required = false) String option, @RequestParam(value = "keyword", required = false) String keyword) {
        log.info("postListGet()");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);

        model.addAttribute("currentPage", criteria.getPage());
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("baseUri", "/board/post/list");

        if (option == null && keyword == null) {
            pageMaker.setTotalCount(postService.countAllPost());
            model.addAttribute("postList", postService.selectAllPost(criteria));

        } else {
            pageMaker.setTotalCount(postService.countAllSearchedPost(option, keyword));
            model.addAttribute("postList", postService.selectAllSearchedPost(criteria, option, keyword));
            model.addAttribute("option", option);
            model.addAttribute("keyword", keyword);
        }

        return "/board/post/list";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/post/{id}")
    public String postGet(@PathVariable("id") long id, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("postGet()");

        model.addAttribute("post", postService.selectPostById(id));

        List<Comment> commentList = commentService.selectAllComment(id);
        model.addAttribute("commentList", commentList);

        if (! model.containsAttribute("comment")) {
            model.addAttribute("comment", new Comment());
        }

        if (customUserDetails != null) {
            String userNickname = getAuthenticatedUser().getNickname();
            int isPostLike = postService.countPostLikeByNickname(id, userNickname);
            model.addAttribute("isPostLike", isPostLike);
            model.addAttribute("isCommentLikeList", commentService.selectAllCommentLike(id, userNickname));
        }

        postService.updatePostByViewCount(id);

        return "/board/post";
    }

    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.POST)
    @GetMapping("/post/{id}/edit")
    public String postEditGet(@PathVariable("id") long id, Model model) throws IOException {
        log.info("postEditGet()");

        model.addAttribute("post", postService.selectPostById(id));

        return "/board/post/edit";
    }

    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.POST)
    @PostMapping("/post/{id}/edit")
    public String postEditPost(@PathVariable("id") long id, @ModelAttribute("post") @Valid Post post, BindingResult result, Model model, RedirectAttributes attributes) throws IOException {
        log.info("postEditPost()");

        if (result.hasErrors()) {
            model.addAttribute(FAILURE_MESSAGE, "게시글 제목 또는 내용을 확인해주세요.");
            return "/board/post/edit";

        } else {
            post.setUserNickname(getAuthenticatedUser().getNickname());
            postService.updatePost(post);

            attributes.addFlashAttribute(SUCCESS_MESSAGE, "게시글이 수정되었습니다.");
            return "redirect:/board/post/" + id;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @Identification(type = Identification.Type.POST)
    @PostMapping("/post/{id}/delete")
    public String postDeleteRequest(@PathVariable("id") long id, RedirectAttributes attributes) throws IOException {
        log.info("postDeleteRequest()");

        postService.deletePost(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));

        attributes.addFlashAttribute(SUCCESS_MESSAGE, "게시글이 삭제되었습니다.");

        return "redirect:/board/post/list";
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/{id}/like")
    public Integer postLikePost(@PathVariable("id") long id) {
        log.info("postLikePost()");

        return postService.updatePostByLikeCount(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/comment/new")
    public String commentNewPost(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, RedirectAttributes attributes) {
        log.info("commentNewPost()");

        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.comment", result);
            attributes.addFlashAttribute("comment", comment);
            attributes.addFlashAttribute(FAILURE_MESSAGE, "댓글 내용을 확인해주세요.");

        } else {
            comment.setUserNickname(Optional.ofNullable(getAuthenticatedUser()
                    .getNickname())
                    .orElse("익명"));

            commentService.createComment(comment);
            postService.updatePostByCommentCount(comment.getPostId(), Variables.Command.UP_COMMAND);
        }
        return "redirect:/board/post/" + comment.getPostId();
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @Identification(type = Identification.Type.COMMENT)
    @GetMapping("/comment/{id}/edit")
    public Map<String, Object> commentEditGet(@PathVariable("id") long id) {
        log.info("commentEditGet()");

        Map<String, Object> map = new HashMap<>();
        Comment comment = commentService.selectCommentById(id);
        comment.setUserNickname(Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
        map.put("comment", comment);

        return map;
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @Identification(type = Identification.Type.COMMENT)
    @PostMapping("/comment/{id}/edit")
    public Map<String, Object> commentEditPost(@PathVariable("id") long id, @RequestBody @Valid Comment comment, BindingResult result) {
        log.info("commentEditPost()");

        Map<String, Object> map = new HashMap<>();

        if (result.hasErrors()) {
            map.put("error", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            map.put("result", 0);

        } else {
            comment.setUserNickname(Optional.ofNullable(getAuthenticatedUser()
                    .getNickname())
                    .orElse("익명"));

            map.put("result", commentService.updateComment(comment));
        }

        return map;
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @Identification(type = Identification.Type.COMMENT)
    @PostMapping("/comment/{id}/delete")
    public Integer commentDeletePost(@PathVariable("id") long id, @RequestBody Comment comment) {
        log.info("commentDeletePost()");

        postService.updatePostByCommentCount(comment.getPostId(), Variables.Command.DOWN_COMMAND);
        return commentService.deleteComment(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/{id}/like")
    public Integer commentLikePost(@PathVariable("id") long id, @RequestBody Map<String, Object> map) {
        log.info("commentLikePost()");

        long postId = Long.parseLong(map.get("postId").toString());
        long commentId = Long.parseLong(map.get("commentId").toString());

        return commentService.updateCommentLike(postId, commentId, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명"));
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @PostMapping("/post/{id}/check")
    public Integer postCheckPost(@PathVariable("id") long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("postCheckPost()");

        return (postService.countPostByNickname(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명")) == 1) ? 1 : 0;
    }

    @ResponseBody
    @PreAuthorize("permitAll()")
    @PostMapping("/comment/{id}/check")
    public Integer commentCheckPost(@PathVariable("id") long id, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        log.info("commentCheckPost()");

        return (commentService.countCommentByNickname(id, Optional.ofNullable(getAuthenticatedUser()
                .getNickname())
                .orElse("익명")) == 1) ? 1 : 0;
    }
}