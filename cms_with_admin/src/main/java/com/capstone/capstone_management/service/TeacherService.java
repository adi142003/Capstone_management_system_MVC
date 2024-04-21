package com.capstone.capstone_management.service;

import com.capstone.capstone_management.models.Teacher;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    String GetTeacherPassword(String TRN);
    Teacher getTeacher(String TRN);
}
