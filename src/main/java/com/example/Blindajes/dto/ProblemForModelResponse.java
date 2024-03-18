package com.example.Blindajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemForModelResponse {
    private String modelo;
    private Long errores;
    private Double porcentajeError;
}
