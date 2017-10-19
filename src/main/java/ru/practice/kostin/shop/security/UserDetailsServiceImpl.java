package ru.practice.kostin.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.persistence.entity.RoleEntity;
import ru.practice.kostin.shop.persistence.entity.UserEntity;
import ru.practice.kostin.shop.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userService.getUserByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        for (RoleEntity roleEntity : user.getRoles()){
            roles.add(new SimpleGrantedAuthority(roleEntity.getName().name()));
        }
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPasswordHash(), roles);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
