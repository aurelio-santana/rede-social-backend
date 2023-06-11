package com.sysmap.socialNetwork.services.follow;



import java.util.List;
import java.util.UUID;

public interface IFollowService {

    String followAndUnfollowUser(FollowUserRequest request);
    GetAllFollowsResponse getAllFollows();
    GetFollowsListByUserId getFollowsListByUserId(UUID userId);
    //Follow getFollowingListByUserId(UUID userId);
    List<FindAllUsersFollowResponse> getAllUsersWithFollow();
}
