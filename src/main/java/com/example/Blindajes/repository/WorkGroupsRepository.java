package com.example.Blindajes.repository;
import com.example.Blindajes.model.WorkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkGroupsRepository extends JpaRepository<WorkGroup, Long> {
    WorkGroup findWorkGroupsByName(String name);

    @Query(nativeQuery = true,
            value = "SELECT wg.* FROM work_group wg " +
                    "WHERE wg.hidden = 0")
    List<WorkGroup> findAllWGHiddenFalse();

    @Query(nativeQuery = true,
        value = "select * " +
                "from work_group wg " +
                "join model_workgroup m_wg on wg.id = m_wg.work_group_id " +
                "where hidden = 0 AND brand_model_id = 4")
    List<WorkGroup> findFilteredWGByVisibilityAndBrandModel(Long brand);
}
