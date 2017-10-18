package ru.practice.kostin.shop.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.practice.kostin.shop.persistence.entity.RoleEntity;
import ru.practice.kostin.shop.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private Integer id;
    private String email;
    private String passwordHash;
    private List<GrantedAuthority> roles;

    public CustomUserDetails(UserEntity user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
        this.roles = new ArrayList<>();
        for (RoleEntity roleEntity : user.getRoles()){
            this.roles.add(new SimpleGrantedAuthority(roleEntity.getName().name()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
