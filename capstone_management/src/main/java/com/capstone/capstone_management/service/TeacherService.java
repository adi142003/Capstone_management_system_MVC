package com.capstone.capstone_management.service;

import com.capstone.capstone_management.models.Teacher;

import java.util.Optional;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    Teacher getTeacher(String TRN);

    Optional<Teacher> getTeacherbyid(Teacher teacher);
}
