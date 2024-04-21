package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.ProjectRepository;
import com.capstone.capstone_management.repository.StudentRepository;
import com.capstone.capstone_management.repository.UserAccountRepository;
import com.capstone.capstone_management.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public String GetStudentPassword(String SRN) {
        Optional<Student> t = studentRepository.findById(SRN);
        UserAccount s = t.get().getUserAccount();
        return s.getPassword();
    }
}
