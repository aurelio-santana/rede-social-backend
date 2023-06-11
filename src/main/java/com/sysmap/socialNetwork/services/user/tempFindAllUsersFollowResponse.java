package com.sysmap.socialNetwork.services.user;

import java.util.List;
import java.util.UUID;

public class tempFindAllUsersFollowResponse {
    public UUID id;
    public String name;
    public String email;
    public String photoUri;
    public List<UUID> following;
    public List<UUID> followers;

    public tempFindAllUsersFollowResponse(UUID id, String name, String email, String photoUri,
                                          List<UUID> following, List<UUID> followers) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photoUri = photoUri;
        this.following = following;
        this.followers = followers;
    }
}


