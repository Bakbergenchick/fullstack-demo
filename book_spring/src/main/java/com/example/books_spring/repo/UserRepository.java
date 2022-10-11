package com.example.books_spring.repo;

import com.example.books_spring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByEmail(String email);

    boolean existsUsersByEmail(String email);
}
