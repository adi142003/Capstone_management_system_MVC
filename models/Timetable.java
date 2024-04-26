package com.capstone.capstone_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reviewphase;
    @ManyToOne()
    @JoinColumn(name = "panel_id")
    private Panel panel;
    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;
    private LocalDate date;
    private LocalTime time;
}
