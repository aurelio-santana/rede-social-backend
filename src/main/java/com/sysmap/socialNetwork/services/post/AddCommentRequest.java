package com.sysmap.socialNetwork.services.post;

import com.sysmap.socialNetwork.entities.Comment;
import lombok.Data;

import java.util.UUID;

@Data
public class AddCommentRequest {
    public UUID postId;
    public Comment comment;
}
