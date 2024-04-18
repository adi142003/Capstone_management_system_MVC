package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.repository.*;
import com.capstone.capstone_management.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private UserAccountRepository userAccountRepository;
    private PanelRepository panelRepository;
    private ReviewRepository reviewRepository;
    private ProjectRepository projectRepository;
    private TimetableRepository timetableRepository;

    public AdminServiceImpl(UserAccountRepository userAccountRepository, PanelRepository panelRepository, ReviewRepository reviewRepository, ProjectRepository projectRepository, TimetableRepository timetableRepository) {
        this.userAccountRepository = userAccountRepository;
        this.panelRepository = panelRepository;
        this.reviewRepository = reviewRepository;
        this.projectRepository = projectRepository;
        this.timetableRepository = timetableRepository;
    }
}
