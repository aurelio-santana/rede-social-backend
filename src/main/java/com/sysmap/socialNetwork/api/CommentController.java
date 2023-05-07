package com.sysmap.socialNetwork.api;
import com.sysmap.socialNetwork.services.comment.CommentService;
import com.sysmap.socialNetwork.services.comment.CreateCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService _commentService;
    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CreateCommentRequest request) {
        var response = _commentService.createComment(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
