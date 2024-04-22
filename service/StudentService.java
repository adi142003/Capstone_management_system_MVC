package com.capstone.capstone_management.service;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.UserAccount;
import org.springframework.stereotype.Service;
import java.util.List;

public interface StudentService {
    Student findStudent(UserAccount userAccount);

    Student saveStudent(Student student);
    Project saveProject(Project project);

    String GetStudentPassword(String srn);

    Project findProject(int teamid);

    Project getProject(String srn);
    List<Student> getTeamMembersByStudentUSN(String usn);
    List<Student> getStudentsByTeacherId(int teacherId); //
}