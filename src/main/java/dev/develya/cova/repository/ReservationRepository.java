package dev.develya.cova.repositories;

import dev.develya.cova.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Add custom query methods if needed
}
