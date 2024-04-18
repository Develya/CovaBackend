package dev.develya.cova.repositories;

import dev.develya.cova.model.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Integer> {
    // Add custom query methods if needed
}
