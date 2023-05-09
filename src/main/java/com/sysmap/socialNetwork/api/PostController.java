package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.entities.Post;
import com.sysmap.socialNetwork.services.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private IPostService _postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request) {
        var response = _postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<String> updatePost(String postId, @RequestBody UpdatePostRequest request) {
        var response = _postService.updatePost(postId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePost(String postId) {
        var response = _postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<FindPostResponse> getPost(UUID id) {
        return ResponseEntity.ok().body(_postService.findPostById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts(){
        var response = ResponseEntity.ok().body(_postService.getAllPosts());
        return response;
    }

    @GetMapping("/feed")
    public ResponseEntity<List<Post>> feed(String userId){
        var response = ResponseEntity.ok().body(_postService.feed(userId));
        return response;
    }

//    @GetMapping
//    public ResponseEntity<FindPostResponse> Feed(UUID id) {
//        return ResponseEntity.ok().body(_postService.findPostById(id));
//    }
}
