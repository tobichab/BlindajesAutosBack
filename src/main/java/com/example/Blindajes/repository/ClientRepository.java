package com.example.Blindajes.repository;

import com.example.Blindajes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByName(String name);

    @Query(nativeQuery = true,
            value = "SELECT c.* FROM client c " +
                    "WHERE c.hidden = 0")
    List<Client> findAllClientHiddenFalse();
}
