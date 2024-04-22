package com.capstone.capstone_management.service;

import java.util.List;
import java.util.Optional;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.Review;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    String GetTeacherPassword(String TRN);
    Teacher getTeacher(String TRN);
    List<Project> getProjectsByTeacherId(int teacherId);
    List<Project> getProjectsByYear(int year);
    List<Project> getAllProjects();
    Optional<Teacher> getTeacherbyid(Teacher teacher);
    void saveReview(Review review);
    Teacher getTeacherById(int teacherId);
}
