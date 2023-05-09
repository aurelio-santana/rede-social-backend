package com.sysmap.socialNetwork.services.comment;

public interface ICommentService {
    String createComment(CreateCommentRequest request);
    String updateComment(UpdateCommentRequest request);
    String deleteComment(DeleteCommentRequest request);
}
