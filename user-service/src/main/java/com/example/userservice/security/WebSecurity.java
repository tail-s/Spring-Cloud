package com.example.userservice.security;

import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/**").permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/**")).addFilter(getAuthenticationFilter())
                // h2-console의 default url이 아닌 yml파일에서 수정한 경우에도 접근 가능하도록 열여주기
                .authorizeHttpRequests(auth -> auth.requestMatchers(PathRequest.toH2Console()).permitAll())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }



//    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
//        return new AuthenticationFilter(authenticationManager);
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }


}
