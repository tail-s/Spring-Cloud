package com.example.userservice.security;

import com.example.userservice.dto.UserDto;
import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final UserFindPort userFindPort;
    private final Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
//                                UserFindPort userFindPort,
                                Environment environment) {
        super.setAuthenticationManager(authenticationManager);
//        this.userFindPort = userFindPort;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(creds.getEmail(),
                    creds.getPassword(), new ArrayList<>());
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
//        String username = authResult.getName();
//        UserDto user = userFindPort.findUserByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        log.debug("user id {}", user.getUserId());
//
//        String token = Jwts.builder()
//                .setSubject(user.getUserId())
//                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration.time"))))
//                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
//                .compact();
//        response.addHeader("token", token);
//        response.addHeader("userId", user.getUserId());
    }
}
