package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.*;
import com.capstone.capstone_management.service.TeacherService;
import org.apache.catalina.User;
import org.hibernate.mapping.List;
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
    public java.util.List<Project> getProjectsByTeacherId(int teacherId){
        return projectRepository.findByTeacherId(teacherId);
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
        Optional<Teacher> t = teacherRepository.findById(TRN);
        UserAccount s = t.get().getUseraccount();
        return s.getPassword();
    }

    @Override
    public Teacher getTeacher(String trn) {
        UserAccount s = userAccountRepository.findByUsn(trn);
        return teacherRepository.findByUseraccount(s);
    }
    @Override
    public Optional<Teacher> getTeacherbyid(Teacher teacher) {
        return teacherRepository.findById(Long.valueOf(teacher.getId()));
    }
}
