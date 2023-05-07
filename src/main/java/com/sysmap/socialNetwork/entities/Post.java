package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Post {

    private UUID id;
    private String content;
    private Integer likes;
    private UUID userId;
    private List<Comment> comment;
    private List<UUID> like;
    //TODO private date datePost;

    public Post(String content, Integer likes, UUID userId) {
        this.setId();
        this.content = content;
        this.likes = likes;
        this.userId = userId;
        this.comment = new ArrayList<>();
        this.like = new ArrayList<>();
    }

    protected void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }



}

