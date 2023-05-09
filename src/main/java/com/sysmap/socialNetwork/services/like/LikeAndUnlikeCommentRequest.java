package com.sysmap.socialNetwork.services.like;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeAndUnlikeCommentRequest {
    public UUID postId;
    public UUID commentId;
    public UUID userId;

    public LikeAndUnlikeCommentRequest(UUID postId, UUID commentId, UUID userId) {
        this.postId = postId;
        this.commentId = commentId;
        this.userId = userId;
    }
}
