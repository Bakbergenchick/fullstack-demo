package com.example.books_spring.repo;

import com.example.books_spring.entity.RoleType;
import com.example.books_spring.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRoleType(RoleType type);
}
