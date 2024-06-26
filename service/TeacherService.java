package com.capstone.capstone_management.service;

import java.util.List;
import java.util.Optional;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Teacher;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    String GetTeacherPassword(String TRN);
    Teacher getTeacher(String TRN);
    List<Project> getProjectsByTeacher(String teacherId);
    List<Project> getProjectsByYear(int year);
    List<Project> getAllProjects();
    Optional<Teacher> getTeacherbyid(int teacher);
}