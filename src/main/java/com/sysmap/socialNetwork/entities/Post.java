package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Post {

    private UUID id;
    private String title;
    private String content;
    private UUID userId;
    private String name;
    private List<Comment> comment;
    private List<UUID> like;
    private Integer likes;
    private LocalDateTime createdAt;
    private List<String> photoUri;
    //TODO updatedAt;

    public Post(String title, String content, UUID userId, String name, LocalDateTime createdAt) {
        this.setId();
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.name = name;
        this.comment = new ArrayList<>();
        this.like = new ArrayList<>();
        this.likes = 0;
        this.createdAt = createdAt;
        this.photoUri = new ArrayList<>();

    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }



}

