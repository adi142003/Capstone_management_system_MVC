package com.capstone.capstone_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ReviewID;

    private String ReviewPhase;
    private int Evalmark1;
    private int Evalmark2;
    private int Evalmark3;
    private int Evalmark4;
    private String ReviewComment;

    @ManyToOne
    @JoinColumn(name = "teacher_id") // Foreign key column in Review table referencing Teacher
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id") // Foreign key column in Review table referencing Student
    private Student student;

    // Constructors, getters, and setters
}
