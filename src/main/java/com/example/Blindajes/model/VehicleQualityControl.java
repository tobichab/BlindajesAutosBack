package com.example.Blindajes.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VehicleQualityControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
    private LocalDate qualityControlDate = LocalDate.now();
    private Boolean canBeCheckedOut = false;

    @OneToMany (mappedBy = "vehicleQualityControl", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkGroupProblem> workGroupProblemList;


    public String getFormattedLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return qualityControlDate.format(formatter);
    }

}
