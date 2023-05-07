package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.security.IJwtService;
import com.sysmap.socialNetwork.services.user.CreateUserRequest;
import com.sysmap.socialNetwork.services.user.FindUserResponse;
import com.sysmap.socialNetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    //COMENTÁRIOS APENAS PARA REFORÇAR E FACILITAR O ESTUDO, SERÃO RETIRADOS NO FINAL DO PROJETO.


    @Autowired
    private IUserService _userService;

    @Autowired
    private IJwtService _jwtService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        //Resposta HTTP<retorna String Id>
        //Chama o Service [UserService] createUser(
        //@No corpo da requisição será passado a model de criação de usuário CreateUserRequest

        //TODO: Classe de validação separada
        //        if (request.name.length() < 3) {
        //            //return ResponseEntity.badRequest().body("Invalid user"); //badRequest: 404.
        //            return ResponseEntity.unprocessableEntity().body("Invalid user"); //...: 422.
        //        }

        //if (!_jwtService.isValidToken(getToken(), getUserId())) {//TODO teste sintrng
        if (!_jwtService.isValidToken(getToken(), getUserId())) {
            System.out.println(getToken());
            System.out.println(getUserId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authenticate");
        }

        var response = _userService.createUser(request);
        //De fato chama o Service com o método de criação de usuário e passa os parâmetros no request

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        //Retorno 201 pro usuário com o Id
    }

    public ResponseEntity<FindUserResponse> getUser(String email) {
        //Resposta HTTP<classe modelo FindUserResponse>
        return ResponseEntity.ok().body(_userService.findUserByEmail(email));
        //Método da Service implementada pela Interface que pesquisa por email
    }

//  @GetMapping("/{id}")
    @GetMapping
    public ResponseEntity<FindUserResponse> getUserById(String userId) {

        return ResponseEntity.ok().body(_userService.findUserById(userId));
    }





    //TODO FAZER DE OUTRA FORMA
    public String getToken() {
        var jwt = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        //Pega o token de autenticação no head
        return jwt.substring(7);
        //remove o prefixo "Bearer " 7 caracteres
    }
    public String getUserId() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("RequestedBy");
        //RequestedBy = userId como parametro no head da requisição
    }
}
