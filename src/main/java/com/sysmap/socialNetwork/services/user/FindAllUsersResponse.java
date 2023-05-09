package com.sysmap.socialNetwork.services.user;
import com.sysmap.socialNetwork.entities.User;

import java.util.List;
public class FindAllUsersResponse {
    public List<User> users;

    public FindAllUsersResponse(List<User> users) {
        this.users = users;
    }
}
