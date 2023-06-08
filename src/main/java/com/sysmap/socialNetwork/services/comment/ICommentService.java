package com.sysmap.socialNetwork.services.comment;

public interface ICommentService {
    String createComment(String postId, CreateCommentRequest request);
    String updateComment(UpdateCommentRequest request);
    String deleteComment(DeleteCommentRequest request);
}
