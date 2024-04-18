package dev.develya.cova.repository;

import dev.develya.cova.model.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {
    // Add custom query methods if needed
}
