package dev.develya.cova.repository;

import dev.develya.cova.model.Journey;
import dev.develya.cova.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Add custom query methods if needed

    List<Reservation> findAllByJourney(Journey journey);
}
