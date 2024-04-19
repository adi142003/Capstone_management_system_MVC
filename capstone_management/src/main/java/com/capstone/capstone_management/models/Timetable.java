package com.capstone.capstone_management.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Timetable {
    @Id
    private int id;
    private String ReviewPhase;
    private Date StartDate;
    private Date EndDate;
}
