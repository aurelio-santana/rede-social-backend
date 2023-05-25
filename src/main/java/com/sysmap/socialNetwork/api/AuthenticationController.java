package com.sysmap.socialNetwork.api;

import com.sysmap.socialNetwork.services.authentication.AuthenticateRequest;
import com.sysmap.socialNetwork.services.authentication.AuthenticateResponse;
import com.sysmap.socialNetwork.services.authentication.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authentication")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService _authenticationService;
    @PostMapping
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request) {

        try {
            return ResponseEntity.ok().body(_authenticationService.authenticate(request));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
