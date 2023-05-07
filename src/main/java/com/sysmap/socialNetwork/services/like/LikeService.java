package com.sysmap.socialNetwork.services.like;

import com.sysmap.socialNetwork.entities.Comment;
import com.sysmap.socialNetwork.services.comment.CreateCommentRequest;
import com.sysmap.socialNetwork.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    private PostService _postService;

    public List<UUID> addLikeToPost(String postId, String userId) {
        var response = _postService.addLikeToPost(postId, userId);

        return response;

    }

    public List<UUID> addLikeToComment(String postId, String commentId, String userId) {
        var response = _postService.addLikeToComment(postId, commentId, userId);

        return response;

    }
}
