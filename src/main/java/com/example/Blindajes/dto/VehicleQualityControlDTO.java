package com.example.Blindajes.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleQualityControlDTO {
    private Long id;
    @NotEmpty
    @Length(min = 1, max = 30)
    private String chasis;
    private List<WorkGroupProblemDTO> workGroupProblemDTOList;
}
