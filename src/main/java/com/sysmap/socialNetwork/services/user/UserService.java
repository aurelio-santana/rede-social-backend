package com.sysmap.socialNetwork.services.user;

import com.sysmap.socialNetwork.data.IUserRepository;
import com.sysmap.socialNetwork.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository _userRepository;
    public String createUser(CreateUserRequest request) {
        var user = new User(request.name, request.email, request.password);
        //Cria o usuário usando como modelo a Entity User

        _userRepository.save(user);
        //Salvar usuário criado no Repository

        return user.getId().toString();
        //retorna o Id do usuário pro ResponseEntity
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

    public FindUserResponse findUserById(String userId) {
        System.out.println("1: "+userId);
//        System.out.println("1: "+userId);
       var newid = UUID.fromString(userId);
//        System.out.println("2: "+newid);

        //var user = _userRepository.findUserById(userId).get();
       var user = _userRepository.findById(newid).get();
        //var user = _userRepository.findById(userId).get();
        //Método que faz a consulta no Repository de usuário, interface de acesso IuserRepository
        //.get();

        var response = new FindUserResponse(user.getId(), user.getName(), user.getEmail());
        //Intancia do modelo FindUserResponse

        return response;

    }
}
