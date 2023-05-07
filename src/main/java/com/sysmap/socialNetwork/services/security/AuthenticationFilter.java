package com.sysmap.socialNetwork.services.security;

import com.sysmap.socialNetwork.services.user.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private IJwtService _jwtService;
    @Autowired
    private IUserService _userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (request.getServletPath().contains("/api/v1/user")) {
//            filterChain.doFilter(request, response);
//            return;
//        } //ROTA CREATE USER

        if (request.getServletPath().contains("/api/v1/authentication")) {
            filterChain.doFilter(request, response);
            return;
        } //ROTA LOGIN

        if (request.getServletPath().contains("swagger") || request.getServletPath().contains("docs")) {
            filterChain.doFilter(request, response);
            return;
        } //ROTA SWAGGER

        var token = request.getHeader("Authorization"); //token
        var userId = request.getHeader("RequestedBy"); //userId

        if (token == null || userId == null || !token.startsWith("Bearer ")) {
            response.getWriter().write("User not authenticated");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        } //VALIDA TOKEN RECEBIDO

        boolean isValidToken = false;

        try {
            isValidToken = _jwtService.isValidToken(token.substring(7), userId);
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        } //VALIDA SE O TOKEN É VALIDO

        if (isValidToken) {
            try {
                var user = _userService.getUserById(UUID.fromString(userId));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                response.getWriter().write(e.getMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        } else {
            response.getWriter().write("Invalid token!");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        } //VALIDA TOKEN COM O USUARIO INFORMADO E CRIA O CONTEXTO

        filterChain.doFilter(request, response);
    }
}
