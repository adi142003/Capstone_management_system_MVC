package com.capstone.capstone_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    private String reviewPhase;
    @ElementCollection
    @CollectionTable(name = "schedule_dates", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "date")
    private List<LocalDate> freedates = new ArrayList<>();

    public void addDate(LocalDate date) {
        freedates.add(date);
    }
}
