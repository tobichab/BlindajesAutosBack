package com.example.Blindajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyProductivityResponse {
    private Long productividadActual;
    private Long productividadPasada;
}
