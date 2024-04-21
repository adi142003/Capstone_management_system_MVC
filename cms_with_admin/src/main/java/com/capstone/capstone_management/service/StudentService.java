package com.capstone.capstone_management.service;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Student;
import org.springframework.stereotype.Service;

public interface StudentService {
    Student saveStudent(Student student);
    Project saveProject(Project project);

    String GetStudentPassword(String srn);
}
