package com.sysmap.socialNetwork.services.authentication;

public interface IAuthenticationService {
    AuthenticateResponse authenticate(AuthenticateRequest request) throws Exception;
}
