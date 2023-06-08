package com.sysmap.socialNetwork.services.like;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeAndUnlikePostRequest {
    //public UUID postId;
    public String userId;

    public LikeAndUnlikePostRequest(String userId) {
        //this.postId = postId;
        this.userId = userId;
    }
}
