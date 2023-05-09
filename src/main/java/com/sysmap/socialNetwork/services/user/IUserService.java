package com.sysmap.socialNetwork.services.user;

import com.sysmap.socialNetwork.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);
    String updateUser(String userId, CreateUserRequest request);
    String deleteUser(String userId);
    List<User> getAllUsers();
    User getUser(String email);
    User getUserById(UUID userId);
    FindUserResponse findUserByEmail(String email);
    void uploadPhotoProfile(MultipartFile photo) throws Exception;

}
