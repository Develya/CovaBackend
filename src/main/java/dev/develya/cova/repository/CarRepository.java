package dev.develya.cova.repository;

import dev.develya.cova.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    // Add custom query methods if needed
    List<Car> findByDriver_UserID(int userId);
}
