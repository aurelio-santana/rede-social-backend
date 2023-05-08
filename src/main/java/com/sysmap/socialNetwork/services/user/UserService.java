package com.sysmap.socialNetwork.services.user;

import com.sysmap.socialNetwork.data.IUserRepository;
import com.sysmap.socialNetwork.entities.User;
import com.sysmap.socialNetwork.services.fileUpload.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;
    @Autowired
    private PasswordEncoder _passwordEncoder;
    @Autowired
    private IFileUploadService _fileUploadService;

    public String createUser(CreateUserRequest request) {
        var user = new User(request.name, request.email);

        if (!_userRepository.findUserByEmail(request.email).isEmpty()) {
            return null;
        }

        var hash = _passwordEncoder.encode(request.password);
        user.setPassword(hash);

        _userRepository.save(user);
        return user.getId().toString();
    }

    public FindUserResponse findUserByEmail(String email) {
        var user = _userRepository.findUserByEmail(email).get();
        var response = new FindUserResponse(user.getId(), user.getName(), user.getEmail(), user.getPhotoUri());
        return response;
    }

    public User getUser(String email) {
        return _userRepository.findUserByEmail(email).get();
    }

    public User getUserById(UUID id) {
        return _userRepository.findUserById(id).get();
    }


    public List<User> getUsers(String email) {
        return _userRepository.findAll();
    }


    public void uploadPhotoProfile(MultipartFile photo) throws Exception {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var photoUri = "";

        try {
            var fileName = user.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
            photoUri = _fileUploadService.upload(photo, fileName);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        user.setPhotoUri(photoUri);
        _userRepository.save(user);
    }
}
