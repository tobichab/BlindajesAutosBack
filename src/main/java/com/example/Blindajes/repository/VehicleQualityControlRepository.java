package com.example.Blindajes.repository;
import com.example.Blindajes.model.VehicleQualityControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleQualityControlRepository extends JpaRepository<VehicleQualityControl, Long> {
    List<VehicleQualityControl> findVehicleQualityControlByVehicleId (String id);
}
