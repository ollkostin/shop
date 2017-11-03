package ru.practice.kostin.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.service.dto.product.NewUserDTO;

@Service
public class AuthenticationService {
    private AuthenticationManager authManager;

    /**
     * Authenticates user
     * @param userDTO user credentials
     */
    public void authenticateUser(NewUserDTO userDTO){
        //create token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
        //authenticate user by token
        Authentication authentication = authManager.authenticate(token);
        //set authentication to context manually
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Autowired
    public void setAuthManager(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
}
