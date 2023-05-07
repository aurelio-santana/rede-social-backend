package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.post.CreatePostRequest;
import com.sysmap.socialNetwork.services.post.FindPostResponse;
import com.sysmap.socialNetwork.services.post.PostService;
import com.sysmap.socialNetwork.services.post.UpdatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService _postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request) {
        var response = _postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<FindPostResponse> getPost(UUID id) {
        return ResponseEntity.ok().body(_postService.findPostById(id));
    }

    @PutMapping
    public ResponseEntity<String> updatePost(String postId, UpdatePostRequest request) {
        _postService.updatePost(postId, request);
        return ResponseEntity.ok().body("");
    }
}
