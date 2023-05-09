package com.sysmap.socialNetwork.services.comment;
import com.sysmap.socialNetwork.services.post.IPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private IPostService _postService;

    public String createComment(CreateCommentRequest request) {
        return _postService.createComment(request);
    }

    public String updateComment(UpdateCommentRequest request) {
        return _postService.updateComment(request);

    }

    public String deleteComment(DeleteCommentRequest request) {
        return _postService.deleteComment(request);
    }
}

