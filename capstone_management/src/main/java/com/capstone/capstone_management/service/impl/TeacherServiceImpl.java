package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.*;
import com.capstone.capstone_management.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private UserAccountRepository userAccountRepository;
    private TeacherRepository teacherRepository;
    private PanelRepository panelRepository;
    private ReviewRepository reviewRepository;
    private TimetableRepository timetableRepository;

    public TeacherServiceImpl(UserAccountRepository userAccountRepository, TeacherRepository teacherRepository, PanelRepository panelRepository, ReviewRepository reviewRepository, TimetableRepository timetableRepository) {
        this.userAccountRepository = userAccountRepository;
        this.teacherRepository = teacherRepository;
        this.panelRepository = panelRepository;
        this.reviewRepository = reviewRepository;
        this.timetableRepository = timetableRepository;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
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
