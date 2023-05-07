package com.sysmap.socialNetwork.services.post;

import java.util.UUID;

public class FindPostResponse {

    public UUID id;
    public String content;
    public Integer likes;
    public UUID userId;

    public FindPostResponse(UUID id, String content, Integer likes, UUID userId) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.userId = userId;
    }
}
