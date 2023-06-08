package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.like.ILikeService;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikeCommentRequest;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/post")
public class LikeController {

    @Autowired
    private ILikeService _likeService;

    @PutMapping("/{postId}/like")
    public ResponseEntity<List<UUID>> LikeAndUnlikePost(@PathVariable String postId, @RequestParam("userId") LikeAndUnlikePostRequest request) {
        System.out.println("passou auqi");
        var response = _likeService.LikeAndUnlikePost(postId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/comment/like")
    public ResponseEntity<List<UUID>> LikeAndUnlikeComment(LikeAndUnlikeCommentRequest request) {
        var response = _likeService.LikeAndUnlikeComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
