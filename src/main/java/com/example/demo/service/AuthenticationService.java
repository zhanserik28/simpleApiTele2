package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
        private UserRepository userRepository;
        private HashService hashService;

        public AuthenticationService(UserRepository userRepository, HashService hashService) {
            this.userRepository = userRepository;
            this.hashService = hashService;
        }

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();

            User user = userRepository.findByUsername(username).isPresent() ? userRepository.findByUsername(username).get() : null;
            if (user != null) {
                String encodedSalt = user.getSalt();
                String hashedPassword = hashService.getHashedValue(password, encodedSalt);
                if (user.getPassword().equals(hashedPassword)) {
                    return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
                }
            }

            return null;
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }
}
