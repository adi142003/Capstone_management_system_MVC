package com.capstone.capstone_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.repository.ProjectRepository;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjectsByTeacherPanelId(int teacherId) {
        return projectRepository.findByTeacherPanelId(teacherId);
    }
}
