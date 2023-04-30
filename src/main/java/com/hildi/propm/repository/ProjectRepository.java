package com.hildi.propm.repository;

import com.hildi.propm.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    void deleteById(Long aLong);
}
