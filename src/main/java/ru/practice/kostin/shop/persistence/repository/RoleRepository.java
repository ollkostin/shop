package ru.practice.kostin.shop.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practice.kostin.shop.persistence.entity.RoleEntity;
import ru.practice.kostin.shop.persistence.entity.RoleName;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(RoleName roleName);
}
