package com.example.Blindajes.repository;
import com.example.Blindajes.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByName(String name);

    @Query(nativeQuery = true,
            value = "SELECT b.* FROM brand b " +
                    "WHERE b.hidden = 0")
    List<Brand> findAllBrandHiddenFalse();
}
