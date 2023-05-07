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
    //Pelo menos uma vez por um request ele vai filtrar

    //Essa classe basicamente irá filtrar rotas que não precisa de autenticação

    @Autowired
    private IJwtService _jwtService;
    @Autowired
    private IUserService _userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Garantir que quando chamar uma rota expecífica, ele não vai passar por esse filtro, ele irá pedir autenticação

        if (request.getServletPath().contains("/api/v1/user")) {
            //Não tem pq pedir token de autenticação na rota de login
            filterChain.doFilter(request, response);
            return;
        } //ROTA CREATE USER


        if (request.getServletPath().contains("/api/v1/authentication")) {
            //Não tem pq pedir token de autenticação na rota de login
            filterChain.doFilter(request, response);
            return;
        } //ROTA LOGIN

        if (request.getServletPath().contains("swagger") || request.getServletPath().contains("docs")) {
            //rotas do swagger
            filterChain.doFilter(request, response);
            return;
        } //ROTA SWAGGER

        var token = request.getHeader("Authorization"); //token
        var userId = request.getHeader("RequestedBy"); //userId
        //Quem está fazendo a solicitação, valida se é a dona daquele token

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
                //Com base no userId do requestedBy, pega o usuario que está autenticando

                var authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                //Cria uma model de contexto de autenticação
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //Seta o contexto. É o contexto onde controla que está autenticado e quem não está
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
        //depois de tudo roda o request





    }
}
