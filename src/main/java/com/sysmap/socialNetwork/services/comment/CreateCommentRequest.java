package com.sysmap.socialNetwork.services.comment;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCommentRequest {
    public UUID postId;
    public UUID userId;
    public String content;
}
