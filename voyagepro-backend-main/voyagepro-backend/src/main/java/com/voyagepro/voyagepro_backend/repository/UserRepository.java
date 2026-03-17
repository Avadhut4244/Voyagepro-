package com.voyagepro.voyagepro_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.voyagepro.voyagepro_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}