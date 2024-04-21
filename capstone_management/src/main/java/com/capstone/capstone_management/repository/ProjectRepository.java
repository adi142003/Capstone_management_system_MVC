package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByTeamid(int project);

    Project findByTeamid(int teamid);
}
