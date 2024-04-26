package com.capstone.capstone_management.service.impl;
import java.time.LocalDate;
import java.util.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.capstone.capstone_management.models.Panel;
import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.Timetable;
import com.capstone.capstone_management.repository.ProjectRepository;
import com.capstone.capstone_management.repository.ScheduleRepository;
import com.capstone.capstone_management.repository.TimetableRepository;
import com.capstone.capstone_management.service.TimetableService;
import org.springframework.stereotype.Service;

@Service
public class TimetableServiceImpl implements TimetableService {
    private final ScheduleRepository scheduleRepository;
    private final ProjectRepository projectRepository;
    private final TimetableRepository timetableRepository;

    public TimetableServiceImpl(ScheduleRepository scheduleRepository, ProjectRepository projectRepository, TimetableRepository timetableRepository) {
        this.scheduleRepository = scheduleRepository;
        this.projectRepository = projectRepository;
        this.timetableRepository = timetableRepository;
    }

    @Override
    public List<LocalDate> getCommonDatesForReviewPhase(Panel panel, String reviewPhase) {
        List<Teacher> panelTeachers = panel.getTeachers();
        List<List<LocalDate>> teacherDates = new ArrayList<>();

        // Collect dates for the given review phase from each teacher's schedule
        for (Teacher teacher : panelTeachers) {
            List<LocalDate> dates = scheduleRepository.findFreedatesByTeacherAndReviewPhase(teacher, reviewPhase);
            teacherDates.add(dates);
        }
        System.out.println(teacherDates);
        // Find common dates among all teachers
        Set<LocalDate> commonDatesInAllTeachers = new HashSet<>(teacherDates.get(0));
        for (int i = 1; i < teacherDates.size(); i++) {
            commonDatesInAllTeachers.retainAll(teacherDates.get(i));
        }

        List<LocalDate> commonDates = new ArrayList<>(commonDatesInAllTeachers);

        // If fewer than three common dates found, find dates common between any two teachers
        if (commonDates.size() < 3) {
            Set<LocalDate> commonDatesInAnyTwoTeachers = new HashSet<>();
            for (int i = 0; i < teacherDates.size(); i++) {
                for (int j = i + 1; j < teacherDates.size(); j++) {
                    Set<LocalDate> datesInTwoTeachers = new HashSet<>(teacherDates.get(i));
                    datesInTwoTeachers.retainAll(teacherDates.get(j));
                    datesInTwoTeachers.removeAll(commonDatesInAllTeachers);
                    commonDatesInAnyTwoTeachers.addAll(datesInTwoTeachers);
                }
            }

            List<LocalDate> remainingDates = new ArrayList<>(commonDatesInAnyTwoTeachers);
            remainingDates.sort(LocalDate::compareTo);

            for (LocalDate date : remainingDates) {
                if (commonDates.size() < 3) {
                    commonDates.add(date);
                } else {
                    break;
                }
            }
        }
        System.out.println(commonDates);
        return commonDates;
    }

    @Override
    public List<Timetable> generateTimetable(Panel panel, String reviewPhase, List<LocalDate> commonDates) {
        List<Timetable> timetables = new ArrayList<>();
        System.out.println("1");
        Set<Project> projects = panel.getTeachers().stream()
                .flatMap(teacher -> projectRepository.findByTeacher(teacher).stream())
                .collect(Collectors.toSet());
        System.out.println("2");
        int maxSlotsPerDay = 5; // Maximum 5 slots per day
        int slotDuration = 30; // Each slot is 30 minutes
        System.out.println("3");
        Set<Project> assignedProjects = new HashSet<>(); // Keep track of assigned projects

        for (LocalDate date : commonDates) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            LocalTime currentTime;
            System.out.println("5");
            if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY) {
                currentTime = LocalTime.of(14, 30); // Start time for Monday and Tuesday (2:30 PM)
            } else {
                currentTime = LocalTime.of(16, 0); // Start time for other days (4 PM)
            }
            System.out.println("6");
            int slotCount = 0;

            for (Project project : projects) {
                if (!assignedProjects.contains(project)) {
                    if (slotCount < maxSlotsPerDay) {
                        Timetable timetable = Timetable.builder()
                                .panel(panel)
                                .project(project)
                                .reviewphase(reviewPhase)
                                .date(date)
                                .time(currentTime)
                                .build();
                        System.out.println("7");
                        timetables.add(timetable);
                        timetableRepository.save(timetable); // Save the timetable to the database

                        assignedProjects.add(project); // Mark the project as assigned
                        currentTime = currentTime.plusMinutes(slotDuration);
                        slotCount++;
                    }
                }
            }
        }

        return timetables;
    }

}