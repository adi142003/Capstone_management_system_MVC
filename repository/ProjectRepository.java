package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Panel;
import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    boolean existsByTeamid(int project);

    Project findByTeamid(int teamid);

    List<Project> findByTeacher(Teacher teacher);

    List<Project> findByProjectyear(Year of);
    List<Project> findByTeacherId(int teacherId);

    List<Project> findByPanel(Panel panel);
}
