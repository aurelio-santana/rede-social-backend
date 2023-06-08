package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Comment {
    private UUID id;
    private UUID postId;
    private UUID userId;
    private String name;
    private String content;
    private List<UUID> like;
    private Integer likes;
    private LocalDateTime createdAt;
    //TODO updatedAt;

    public Comment(UUID postId, UUID userId, String name, String content, LocalDateTime createdAt) {
        this.setId();
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.like = new ArrayList<>();
        this.likes = 0;
        this.createdAt = createdAt;
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }
}


