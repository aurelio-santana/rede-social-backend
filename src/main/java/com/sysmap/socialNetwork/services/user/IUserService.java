package com.sysmap.socialNetwork.services.user;

import com.sysmap.socialNetwork.entities.User;

import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);
    FindUserResponse findUserByEmail(String email);
    FindUserResponse findUserById(String userId); //TODO
    User getUserById(UUID userId); //TODO

    User getUser(String email); //TODO
}
