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
        var user = _userService.getUser(request.email);

        if (user == null) { //verifica se é nulo
            return null;
        }
        if (!_passwordEncoder.matches(request.password, user.getPassword())) {
            throw new Exception("Credenciais inválidas!");
        }

        var token = _jwtService.generateToken(user.getId());
        var response = new AuthenticateResponse();

        response.setUserId(user.getId());
        response.setToken(token);

        return response;
    }

}
