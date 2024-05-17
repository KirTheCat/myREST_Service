package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.JwtAuthenticationResponse;
import org.example.model.dto.SignInRequest;
import org.example.model.dto.SignUpRequest;
import org.example.model.enums.RoleEnum;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.model.entity.User;


    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {
        private final UserService userService;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        /**
         * Регистрация пользователя
         *
         * @param request данные пользователя
         * @return токен
         */
        public JwtAuthenticationResponse signUp(SignUpRequest request) {

            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(RoleEnum.ROLE_USER)
                    .build();

            userService.create(user);

            var jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);
        }

        /**
         * Аутентификация пользователя
         *
         * @param request данные пользователя
         * @return токен
         */
        public JwtAuthenticationResponse signIn(SignInRequest request) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            var user = userService
                    .userDetailsService()
                    .loadUserByUsername(request.getUsername());

            var jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);
        }
    }

