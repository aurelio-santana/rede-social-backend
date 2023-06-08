package com.sysmap.socialNetwork.services.like;

import java.util.List;
import java.util.UUID;

public interface ILikeService {

    List<UUID> LikeAndUnlikePost(String postId, LikeAndUnlikePostRequest request);
    List<UUID> LikeAndUnlikeComment(LikeAndUnlikeCommentRequest request);
}
