package com.yunsu.fitmotion.post.controller;

import com.yunsu.fitmotion.post.controller.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @PostMapping
    public ResponseEntity<PostResponse> createPost() {
        PostResponse response = PostResponse.builder()
                .id(1L)
                .title("title")
                .content("content")
                .photoUrl(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<PostResponse> updatePost() {
        PostResponse response = PostResponse.builder()
                .id(1L)
                .title("updated title")
                .content("updated content")
                .photoUrl(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<PostResponse> deletePost() {
        PostResponse response = PostResponse.builder()
                .id(1L)
                .title("deleted")
                .content("deleted")
                .photoUrl(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/like")
    public ResponseEntity<LikeResponse> likePost() {
        LikeResponse response = LikeResponse.builder()
                .code(200)
                .message("liked")
                .likeCount(3)
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/instagram-tag")
    public ResponseEntity<InstagramTagResponse> instagramTag() {
        InstagramTagResponse response = InstagramTagResponse.builder()
                .code(200)
                .message("Instagram tag updated successfully.")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PostListResponse> listPosts() {
        PostResponse post = PostResponse.builder()
                .id(1L)
                .title("title")
                .content("content")
                .photoUrl(null)
                .build();

        PostListResponse response = PostListResponse.builder()
                .code(200)
                .message("Posts fetched successfully.")
                .posts(List.of(post))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> comment() {
        CommentResponse response = CommentResponse.builder()
                .code(201)
                .message("Comment posted successfully.")
                .commentId(1L)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/comment")
    public ResponseEntity<CommentResponse> getComments() {
        CommentResponse response = CommentResponse.builder()
                .code(200)
                .message("Fetched comments.")
                .commentId(1L)
                .build();
        return ResponseEntity.ok(response);
    }
}