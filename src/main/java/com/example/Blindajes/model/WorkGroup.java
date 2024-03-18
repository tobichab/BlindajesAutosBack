package com.example.Blindajes.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean hidden = false;
    private String examples;
    @JsonIgnore
    @OneToMany (mappedBy = "workGroup" , cascade = CascadeType.ALL)
    private List<WorkGroupProblem> workGroupProblemList = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "workGroupsList")
    private List<BrandModel> brandModelList = new ArrayList<>();
}


