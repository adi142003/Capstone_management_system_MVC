package com.capstone.capstone_management.service;

import com.capstone.capstone_management.models.Panel;
import com.capstone.capstone_management.models.Timetable;

import java.time.LocalDate;
import java.util.List;

public interface TimetableService {
    List<LocalDate> getCommonDatesForReviewPhase(Panel panel, String reviewPhase);

    List<Timetable> generateTimetable(Panel panel, String reviewPhase, List<LocalDate> commonDates);
}
