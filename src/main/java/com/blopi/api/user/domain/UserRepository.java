package com.blopi.api.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String Username);

    Boolean existsByUsername(String Username);

    Boolean existsByEmail(String email);
}
