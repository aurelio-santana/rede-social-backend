package com.sysmap.socialNetwork.services.follow;

import lombok.Data;

import java.util.UUID;

@Data
public class FollowUserRequest {
    public UUID userId;
    public UUID userIdToFollow;
}
