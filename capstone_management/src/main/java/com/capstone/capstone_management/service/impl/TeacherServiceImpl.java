package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.repository.*;
import com.capstone.capstone_management.service.TeacherService;
import org.springframework.stereotype.Service;

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
}
