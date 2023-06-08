package com.sysmap.socialNetwork.services.follow;

import com.sysmap.socialNetwork.entities.Follow;

import java.util.UUID;

public interface IFollowService {

    String followAndUnfollowUser(FollowUserRequest request);
    GetAllFollowsResponse getAllFollows();
    GetFollowsListByUserId getFollowsListByUserId(UUID userId);
    //Follow getFollowingListByUserId(UUID userId);
}
