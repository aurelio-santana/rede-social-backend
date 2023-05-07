package com.sysmap.socialNetwork.services.post;

import java.util.UUID;

public interface IPostService {
    String createPost(CreatePostRequest request);
    FindPostResponse findPostById(UUID id);

}
