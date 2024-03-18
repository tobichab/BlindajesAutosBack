package com.example.Blindajes.repository;

import com.example.Blindajes.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findDestinationByName(String name);

    @Query(nativeQuery = true,
            value = "SELECT d.* FROM destination d " +
                    "WHERE d.hidden = 0")
    List<Destination> findAllDestinationHiddenFalse();
}
