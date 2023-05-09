package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.like.ILikeService;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikeCommentRequest;
import com.sysmap.socialNetwork.services.like.LikeAndUnlikePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
public class LikeController {

    @Autowired
    private ILikeService _likeService;

    @PutMapping("/like")
    public ResponseEntity<List<UUID>> LikeAndUnlikePost(LikeAndUnlikePostRequest request) {
        var response = _likeService.LikeAndUnlikePost(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/comment/like")
    public ResponseEntity<List<UUID>> LikeAndUnlikeComment(LikeAndUnlikeCommentRequest request) {
        var response = _likeService.LikeAndUnlikeComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
