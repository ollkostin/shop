package ru.practice.kostin.shop.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ru.practice.kostin.shop.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findOneByEmail(String email);
}
