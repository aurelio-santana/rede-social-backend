package com.sysmap.socialNetwork.services.post;

import java.util.UUID;

public class FindPostResponse {

    public UUID id;
    public String title;
    public String content;
    public Integer likes;
    public UUID userId;

    public FindPostResponse(UUID id, String title, String content, Integer likes, UUID userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.userId = userId;
    }
}
