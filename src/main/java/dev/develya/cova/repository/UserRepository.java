package dev.develya.cova.repository;

import dev.develya.cova.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Add custom query methods if needed
    public boolean existsByEmail(String email);
}
