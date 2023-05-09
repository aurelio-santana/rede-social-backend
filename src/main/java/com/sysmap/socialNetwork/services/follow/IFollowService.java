package com.sysmap.socialNetwork.services.follow;

import com.sysmap.socialNetwork.entities.Follow;

import java.util.List;
import java.util.UUID;

public interface IFollowService {

    String followUser(FollowUserRequest request);
    List<Follow> getAllFollows();
    Follow getFollowerListByUserId(UUID userId);
}
