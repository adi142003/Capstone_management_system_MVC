package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.*;
import com.capstone.capstone_management.service.TeacherService;
import org.apache.catalina.User;
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
}
