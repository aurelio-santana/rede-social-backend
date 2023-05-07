package com.sysmap.socialNetwork.services.user;

import com.sysmap.socialNetwork.entities.User;

import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);
    FindUserResponse findUserByEmail(String email);
    User getUserById(UUID userId);
    User getUser(String email);
}
