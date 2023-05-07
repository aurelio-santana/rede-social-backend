package com.sysmap.socialNetwork.services.authentication;

import com.sysmap.socialNetwork.services.security.IJwtService;
import com.sysmap.socialNetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserService _userService;

    @Autowired
    private IJwtService _jwtService;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    public AuthenticateResponse authenticate(AuthenticateRequest request) throws Exception {
        //Implementando método chamado na Controller

        var user = _userService.getUser(request.email);
        //Busca o user no userRepository pelo email

        if (user == null) { //verifica se é nulo
            return null;
        }
        System.out.println(request.password);
        System.out.println(user.getPassword());

        if (!_passwordEncoder.matches(request.password, user.getPassword())) {
            //Compara a senha, senha recebida como parametro e o hash interno
            throw new Exception("Credenciais inválidas!");

        }

//        if (!user.getPassword().equals(request.password)) {
//            return null;
//        }

        var token = _jwtService.generateToken(user.getId());
        //Gera o token chamando o Service.metodo

        var response = new AuthenticateResponse();

        response.setUserId(user.getId());
        response.setToken(token);

        return response;


    }

}
