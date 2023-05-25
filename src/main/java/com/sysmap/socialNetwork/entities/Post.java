package com.sysmap.socialNetwork.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Post {

    private UUID id;
    private String title;
    private String content;
    private UUID userId;
    private List<Comment> comment;
    private List<UUID> like;
    private Integer likes;
    //TODO private date datePost;

    public Post(String title, String content, UUID userId) {
        this.setId();
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.comment = new ArrayList<>();
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

