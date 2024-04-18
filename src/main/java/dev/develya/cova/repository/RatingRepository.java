package dev.develya.cova.repositories;

import dev.develya.cova.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    // Add custom query methods if needed
}
