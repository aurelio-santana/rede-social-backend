package com.sysmap.socialNetwork.services.user;

import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);
    FindUserResponse findUserByEmail(String email);
    FindUserResponse findUserById(String userId);
}
