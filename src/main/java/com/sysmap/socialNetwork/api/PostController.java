package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private IPostService _postService;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request) {
        var response = _postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePost(String postId, @RequestBody UpdatePostRequest request) {
        var response = _postService.updatePost(postId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(String postId) {
        var response = _postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<FindPostResponse> getPost(UUID id) {
        return ResponseEntity.ok().body(_postService.findPostById(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<FindAllPostsResponse> getAllPosts() {
        var response = ResponseEntity.ok().body(_postService.getAllPosts());
        return response;
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedResponse> feed(String userId) {
        var response = ResponseEntity.ok().body(_postService.feed(userId));
        return response;
    }
}
