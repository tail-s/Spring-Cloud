//package com.example.userservice.security;
//
//import com.example.userservice.dto.UserDto;
//import com.example.userservice.service.UserService;
//import com.example.userservice.vo.RequestLogin;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//@Slf4j
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final UserService userService;
//    private final Environment environment;
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager,
//                                UserService userService,
//                                Environment environment) {
//        super.setAuthenticationManager(authenticationManager);
////        super(authenticationManager);
//        this.userService = userService;
//        this.environment = environment;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(creds.getEmail(),
//                    creds.getPassword(), new ArrayList<>());
//            return getAuthenticationManager().authenticate(token);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//        String userName = ((User)authResult.getPrincipal()).getUsername();
//        UserDto userDetails = userService.getUserDetailsByEmail(userName);
//        if (userDetails == null) {
//            throw new UsernameNotFoundException(userName);
//        }
//        log.debug("user id {}", userDetails.getUserId());
//
//        String token = Jwts.builder()
//                .setSubject(userDetails.getUserId())
//                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration.time"))))
//                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
//                .compact();
//        response.addHeader("token", token);
//        response.addHeader("userId", userDetails.getUserId());
//    }
//}
