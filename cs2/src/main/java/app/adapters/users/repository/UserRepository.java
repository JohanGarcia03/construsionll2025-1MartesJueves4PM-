package app.adapters.users.repository;

import app.adapters.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String username);
    UserEntity findByUserName(String username);
    // findAll() y findById() ya vienen de JpaRepository
}