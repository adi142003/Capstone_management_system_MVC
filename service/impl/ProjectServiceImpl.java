package com.capstone.capstone_management.service.impl;

import java.util.List;
import java.util.Optional;

import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.repository.TeacherRepository;
import com.capstone.capstone_management.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    private TeacherRepository teacherRepository;

    public List<Project> getProjectsByTeacherPanelId(int teacherId) {
        Optional<Teacher> t = teacherRepository.findById(teacherId);
        return projectRepository.findByPanel(t.get().getPanel());
    }
}