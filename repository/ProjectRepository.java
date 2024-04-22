package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Project;
import java.util.List;
import java.time.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    boolean existsByTeamid(int project);
    List<Project> findByTeacherId(int teacherId);
    List<Project> findByProjectyear(Year year);
    Project findByTeamid(int id);
    List<Project> findByTeacherPanelId(int panelId);
}