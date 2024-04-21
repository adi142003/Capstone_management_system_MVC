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
    private int reviewid;
    private String reviewphase;
    private int evalmark1;
    private int evalmark2;
    private int evalmark3;
    private int evalmark4;
    private String reviewcomment;

    @ManyToOne
    @JoinColumn(name = "teacher_id") // Foreign key column in Review table referencing Teacher
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id") // Foreign key column in Review table referencing Student
    private Student student;

    // Constructors, getters, and setters
}
