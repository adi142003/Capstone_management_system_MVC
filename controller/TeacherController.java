package com.capstone.capstone_management.controller;

import com.capstone.capstone_management.models.*;
import com.capstone.capstone_management.repository.PanelRepository;
import com.capstone.capstone_management.repository.ScheduleRepository;
import com.capstone.capstone_management.repository.TeacherRepository;
import com.capstone.capstone_management.repository.TimetableRepository;
import com.capstone.capstone_management.repository.UserAccountRepository;
import com.capstone.capstone_management.service.TeacherService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.capstone.capstone_management.service.TimetableService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserAccountRepository userAccountRepository;
    private final PanelRepository panelRepository;
    private TeacherService teacherService;
    private TimetableService timetableService;
    private TimetableRepository timetableRepository;

    public TeacherController(TeacherRepository teacherRepository, TimetableRepository timetableRepository,ScheduleRepository scheduleRepository, UserAccountRepository userAccountRepository, TeacherService teacherService, TimetableService timetableService, PanelRepository panelRepository) {
        this.teacherRepository = teacherRepository;
        this.scheduleRepository = scheduleRepository;
        this.userAccountRepository = userAccountRepository;
        this.teacherService = teacherService;
        this.timetableService = timetableService;
        this.panelRepository = panelRepository;
        this.timetableRepository=timetableRepository;
    }

    @GetMapping("/teacher/projects/{tsn}")
    public String getProjectsForTeacher(@PathVariable String tsn,Model model){
        // Assuming you have a way to get the logged-in teacher's ID
        // int teacherId = getLoggedInTeacherId();
        String teacherId = tsn;
        model.addAttribute("tsn",tsn);
        List<Project> projects = teacherService.getProjectsByTeacher(teacherId);
        model.addAttribute("projects", projects);

        return "teacher-projects";
    }

    @GetMapping("/timetable/{tsn}")
    public String getTimetable(@PathVariable String tsn,Model model) {
        List<Timetable> slots = timetableRepository.findAll();
        model.addAttribute("timetable", slots);
        model.addAttribute("tsn",tsn);
        return "timetable";
    }

    @GetMapping("/projects/year/{tsn}")
    public String getProjectsByYear(@PathVariable String tsn,@RequestParam(value="year",required = false) Integer year, Model model) {
        List<Project> projects;
        if (year != null) {
            projects = teacherService.getProjectsByYear(year);
        } else {
            projects = teacherService.getAllProjects();
        }
        model.addAttribute("projects", projects);
        model.addAttribute("tsn",tsn);

        return "projects-by-year";
    }

    private int getLoggedInTeacherId() {
        // Implement your logic to retrieve the logged-in teacher's ID
        // For example, from the session or a security context
        return 1; // Replace with the actual teacher ID
    }

    @GetMapping("/teacher/freeslot/{tsn}")
    public String getFreeslotForTeacher(@PathVariable String tsn, Model model) {
        FreeSlotsForm fd = new FreeSlotsForm();
        model.addAttribute("tsn",tsn);
        model.addAttribute("freedate",fd);
        return "teacher-freeslot";
    }

    @PostMapping("/teacher/freeslot/{tsn}")
    public String postFreeslotForTeacher(@PathVariable String tsn, Model model,@ModelAttribute("reviewphase") String revp,@ModelAttribute("freedate") FreeSlotsForm fd) {
        Optional<UserAccount> us = userAccountRepository.findById(tsn);
        System.out.println(tsn);
        Teacher teacher = teacherRepository.findByUseraccount(us.orElse(null));
        Schedule sc = new Schedule();
        sc.setTeacher(teacher);
        System.out.println(fd.getSelectedDate0());
        sc.addDate(fd.getSelectedDate0());
        System.out.println("6");
        sc.addDate(LocalDate.parse(fd.getSelectedDate1()));
        System.out.println("7");
        sc.addDate(LocalDate.parse(fd.getSelectedDate2()));
        System.out.println("1");
        sc.setReviewPhase(fd.getReviewPhase());
        System.out.println("2");
        scheduleRepository.save(sc);
        System.out.println("3");
        int ids = teacher.getPanel().getPanelno();
        Panel panel = teacher.getPanel();
        List<Teacher> panelTeachers = panel.getTeachers();
        boolean allTeachersHaveEntries = panelTeachers.stream()
                .allMatch(t -> scheduleRepository.existsByTeacherAndReviewPhase(t, fd.getReviewPhase()));

        List<LocalDate> lde;
        List<Timetable> tb;
        if (allTeachersHaveEntries) {
            lde = timetableService.getCommonDatesForReviewPhase(panel, fd.getReviewPhase());
            System.out.println(lde);
            tb = timetableService.generateTimetable(panel,fd.getReviewPhase(),lde);
        }
        return "redirect:/teacher/freeslot/" + tsn;
    }

    @Data
    public class FreeSlotsForm {
        private String reviewPhase;
        private LocalDate selectedDate0;
        private String selectedDate1;
        private String selectedDate2;

        // Getters and setters
    }
}