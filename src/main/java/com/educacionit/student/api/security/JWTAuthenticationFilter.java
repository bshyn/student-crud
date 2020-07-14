package com.educacionit.student.api.security;

import com.educacionit.student.api.entity.UserEntity;
import com.educacionit.student.api.repository.IUserRepository;
import com.educacionit.student.api.service.IUserService;
import com.educacionit.student.api.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.educacionit.student.api.security.SecurityConstants.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private IUserService<UserEntity> userService;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, IUserService<UserEntity> userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp){
        try{
            UserEntity credentials = new ObjectMapper().readValue(req.getInputStream(), UserEntity.class);
            
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()
                    )
            );
        } catch (IOException e){
            // TODO: Integrar con excepciones custom
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();

        UserEntity user = userService.findByUsername(username);
        String authorities = UserDetailsServiceImpl.getAuthorities(user).stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        String token = JWT.create()
                .withSubject(username)
                .withClaim(AUTHORITIES_KEY, authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

}
