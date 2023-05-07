package com.sysmap.socialNetwork.services.comment;
import com.sysmap.socialNetwork.entities.Comment;
import com.sysmap.socialNetwork.services.post.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private PostService _postService;

    public String createComment(CreateCommentRequest request) {
        var comment = new Comment(request.postId, request.userId, request.content);
        _postService.addComments(request.postId, comment);

        return comment.getId().toString();
    }
}
