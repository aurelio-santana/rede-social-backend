package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.comment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/post/{postId}/comment")
public class CommentController {

    @Autowired
    private ICommentService _commentService;

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@PathVariable String postId, @RequestBody CreateCommentRequest request) {
        var response = _commentService.createComment(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateComment(@RequestBody UpdateCommentRequest request) {
        var response = _commentService.updateComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteComment(@RequestBody DeleteCommentRequest request) {
        var response = _commentService.deleteComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
