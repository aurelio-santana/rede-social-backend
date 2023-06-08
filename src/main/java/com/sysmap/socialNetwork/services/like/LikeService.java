package com.sysmap.socialNetwork.services.like;
import com.sysmap.socialNetwork.services.post.IPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeService implements ILikeService {

    @Autowired
    private IPostService _postService;

    public List<UUID> LikeAndUnlikePost(String postId, LikeAndUnlikePostRequest request) {
        var response = _postService.LikeAndUnlikePost(postId, request);
        return response;
    }

    public List<UUID> LikeAndUnlikeComment(LikeAndUnlikeCommentRequest request) {
        var response = _postService.LikeAndUnlikeComment(request);
        return response;
    }
}
