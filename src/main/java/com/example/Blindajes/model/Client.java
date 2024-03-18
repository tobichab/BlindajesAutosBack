package com.example.Blindajes.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean hidden = false;
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList = new ArrayList<>();
}
