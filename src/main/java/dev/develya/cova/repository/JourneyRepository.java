package dev.develya.cova.repository;

import dev.develya.cova.model.Journey;
import dev.develya.cova.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {
    // Add custom query methods if needed

    List<Journey> findAllByTrajet(Optional<Trajet> trajet);
}
