package com.sysmap.socialNetwork.services.comment;

import lombok.Data;

import java.util.UUID;
@Data
public class DeleteCommentRequest {
    public UUID postId;
    public UUID commentId;
}
