package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Schedule;
import com.capstone.capstone_management.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT s.freedates FROM Schedule s WHERE s.teacher = :teacher AND s.reviewPhase = :reviewPhase")
    List<LocalDate> findFreedatesByTeacherAndReviewPhase(@Param("teacher") Teacher teacher, @Param("reviewPhase") String reviewPhase);

    boolean existsByTeacherAndReviewPhase(Teacher t, String reviewPhase);
}
