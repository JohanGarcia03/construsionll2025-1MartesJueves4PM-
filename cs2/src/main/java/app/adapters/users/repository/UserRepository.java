package app.adapters.users.repository;

import app.adapters.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserName(String username);
    Optional<UserEntity> findByUserName(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.person.cedula = :cedula")
    Optional<UserEntity> findByCedula(@Param("cedula") Long cedula);

}