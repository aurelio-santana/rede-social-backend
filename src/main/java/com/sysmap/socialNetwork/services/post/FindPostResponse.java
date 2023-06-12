package com.sysmap.socialNetwork.services.post;

import com.sysmap.socialNetwork.entities.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FindPostResponse {

    public UUID id;
    public String title;
    public String content;

    public List<Comment> comment;
    public List<UUID> like;
    public Integer likes;
    public UUID userId;
    public String name;
    public LocalDateTime createdAt;
    public List<String> photoUri;


    public FindPostResponse(UUID id, String title, String content, List<Comment> comment, List<UUID> like,
                            Integer likes, UUID userId, String name, LocalDateTime createdAt, List<String> photoUri) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.like = like;
        this.likes = likes;
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
        this.photoUri = photoUri;
    }
}


