package com.example.petnote.repository;


import com.example.petnote.entity.OuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<OuthUser, Long> {
    Optional<OuthUser> findByEmail(String email);
}
