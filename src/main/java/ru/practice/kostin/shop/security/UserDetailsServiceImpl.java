package ru.practice.kostin.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.persistence.entity.UserEntity;
import ru.practice.kostin.shop.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userService.getUserByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        return new CustomUserDetails(user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
