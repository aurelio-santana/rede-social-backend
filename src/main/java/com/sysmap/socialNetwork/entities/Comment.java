package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Comment {
    private UUID id;
    private UUID postId;
    private UUID userId;
    private String content;
    private List<UUID> like;
    private Integer likes;

    public Comment(UUID postId, UUID userId, String content) {
        this.setId();
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.like = new ArrayList<>();
        this.likes = 0;
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }
}


