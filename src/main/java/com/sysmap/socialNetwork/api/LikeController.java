package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.like.LikeService;
import com.sysmap.socialNetwork.services.post.PostService;
import com.sysmap.socialNetwork.services.post.UpdatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

    @Autowired
    private LikeService _likeService;

    @PutMapping("/post")
    public ResponseEntity<List<UUID>> addLikeToPost(String postId, String userId) {
        var response = _likeService.addLikeToPost(postId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/comment")
    public ResponseEntity<List<UUID>> addLikeToComment(String postId, String commentId, String userId) {
        var response = _likeService.addLikeToComment(postId, commentId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
