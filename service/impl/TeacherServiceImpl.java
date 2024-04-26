package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.*;
import com.capstone.capstone_management.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.Year;


@Service
public class TeacherServiceImpl implements TeacherService {

    private UserAccountRepository userAccountRepository;
    private TeacherRepository teacherRepository;
    private PanelRepository panelRepository;
    private ReviewRepository reviewRepository;
    private TimetableRepository timetableRepository;
    private ProjectRepository projectRepository;

    public TeacherServiceImpl(UserAccountRepository userAccountRepository, ProjectRepository projectRepository, TeacherRepository teacherRepository, PanelRepository panelRepository, ReviewRepository reviewRepository, TimetableRepository timetableRepository) {
        this.userAccountRepository = userAccountRepository;
        this.teacherRepository = teacherRepository;
        this.panelRepository = panelRepository;
        this.reviewRepository = reviewRepository;
        this.timetableRepository = timetableRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public java.util.List<Project> getProjectsByTeacher(String teacherId){
        UserAccount us = userAccountRepository.findByUsn(teacherId);
        Teacher teacher = teacherRepository.findByUseraccount(us);
        return projectRepository.findByTeacher(teacher);
    }

    @Override
    public java.util.List<Project> getProjectsByYear(int year){
        return projectRepository.findByProjectyear(Year.of(year));
    }

    public java.util.List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public String GetTeacherPassword(String TRN) {
        return "";
    }

    @Override
    public Teacher getTeacher(String trn) {
        UserAccount s = userAccountRepository.findByUsn(trn);
        return teacherRepository.findByUseraccount(s);
    }
    @Override
    public Optional<Teacher> getTeacherbyid(int teacher) {
        return teacherRepository.findById(teacher);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
    public Teacher getTeacherById(int teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        return teacherOptional.orElse(null);
    }
}