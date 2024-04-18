package dev.develya.cova.repository;

import dev.develya.cova.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Integer> {
    // Add custom query methods if needed
}
