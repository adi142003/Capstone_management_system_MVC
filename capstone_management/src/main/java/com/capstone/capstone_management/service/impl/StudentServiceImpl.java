package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.repository.ProjectRepository;
import com.capstone.capstone_management.repository.StudentRepository;
import com.capstone.capstone_management.repository.UserAccountRepository;
import com.capstone.capstone_management.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private UserAccountRepository userAccountRepository;
    private StudentRepository studentRepository;
    private ProjectRepository projectRepository;

    public StudentServiceImpl(UserAccountRepository userAccountRepository, StudentRepository studentRepository, ProjectRepository projectRepository) {
        this.userAccountRepository = userAccountRepository;
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
    }
}
