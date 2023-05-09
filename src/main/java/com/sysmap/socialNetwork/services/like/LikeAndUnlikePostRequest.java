package com.sysmap.socialNetwork.services.like;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeAndUnlikePostRequest {
    public UUID postId;
    public UUID userId;

    public LikeAndUnlikePostRequest(UUID postId, UUID userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
