package com.sysmap.socialNetwork.services.security;

import io.jsonwebtoken.Claims;

import java.util.UUID;
import java.util.function.Function;

public interface IJwtService {

    String generateToken(UUID userId);
//    boolean isValidToken(String token, String userId);

    boolean isValidToken(String token, String userId);


}
