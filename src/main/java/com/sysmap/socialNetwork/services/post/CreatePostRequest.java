package com.sysmap.socialNetwork.services.post;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePostRequest {
    public String content;
    public UUID userId;
}


