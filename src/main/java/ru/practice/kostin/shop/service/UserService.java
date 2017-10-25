package ru.practice.kostin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.practice.kostin.shop.exception.PasswordMismatchException;
import ru.practice.kostin.shop.exception.UserAlreadyExistsException;
import ru.practice.kostin.shop.persistence.entity.RoleName;
import ru.practice.kostin.shop.persistence.entity.UserEntity;
import ru.practice.kostin.shop.persistence.repository.RoleRepository;
import ru.practice.kostin.shop.persistence.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserEntity createUser(String email, String password, String confirmPassword) throws UserAlreadyExistsException, PasswordMismatchException {
        UserEntity user = userRepository.findOneByEmail(email);
        if (user != null) {
            throw new UserAlreadyExistsException("user with email " + email + " already exists");
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("password mismatch");
        }
        user = new UserEntity();
        user.setEmail(email);
        user.setPasswordHash(bCryptPasswordEncoder.encode(password));
        user.setRoles(new ArrayList<>());
        user.getRoles().add(roleRepository.findByName(RoleName.ROLE_USER));
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
