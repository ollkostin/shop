package ru.practice.kostin.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.kostin.shop.exception.PasswordMismatchException;
import ru.practice.kostin.shop.exception.UserAlreadyExistsException;
import ru.practice.kostin.shop.persistence.entity.RoleEntity;
import ru.practice.kostin.shop.persistence.entity.RoleName;
import ru.practice.kostin.shop.persistence.entity.UserEntity;
import ru.practice.kostin.shop.persistence.repository.RoleRepository;
import ru.practice.kostin.shop.persistence.repository.UserRepository;
import ru.practice.kostin.shop.service.dto.product.NewUserDTO;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Creates new user
     *
     * @param userDTO user DTO
     * @return created user
     * @throws UserAlreadyExistsException user with specified email exists
     * @throws PasswordMismatchException  password does not match confirmation password
     */
    @Transactional
    public UserEntity createUser(NewUserDTO userDTO) throws UserAlreadyExistsException, PasswordMismatchException {
        UserEntity user = userRepository.findOneByEmail(userDTO.getEmail());
        if (user != null) {
            throw new UserAlreadyExistsException("user with email " + userDTO.getEmail() + " already exists");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new PasswordMismatchException("password mismatch");
        }
        user = new UserEntity();
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        RoleName roleName = RoleName.valueOf("ROLE_" + userDTO.getRole().toUpperCase());
        RoleEntity role = roleRepository.findByName(roleName);
        user.setRoles(Collections.singletonList(role));
        return userRepository.save(user);
    }

    /**
     * Gets user by email
     *
     * @param email email
     * @return user
     */
    @Transactional(readOnly = true)
    public UserEntity getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}
