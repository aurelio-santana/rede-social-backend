package com.sysmap.socialNetwork.services.user;

import com.sysmap.socialNetwork.data.IUserRepository;
import com.sysmap.socialNetwork.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private PasswordEncoder _passwordEncoder;

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
       // método com retorno de model FindUserResponse
        var user = _userRepository.findUserByEmail(email).get();
        //Método que faz a consulta no Repository de usuário, interface de acesso IuserRepository
        //.get();

        var response = new FindUserResponse(user.getId(), user.getName(), user.getEmail());
        //Intancia do modelo FindUserResponse

        return response;

    }

    public User getUser(String email) {
        return _userRepository.findUserByEmail(email).get();
    }

    public User getUserById(UUID id) {
        return _userRepository.findUserById(id).get();
    }
}
