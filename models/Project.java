package com.capstone.capstone_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
//    mentor details here
    @Id
    private int teamid;
    private String domain;
    private Year projectyear;
    private String projecttitle;
    private String projectstatement;
    private String projectdescription;
//    documents handle
    @Transient
    @OneToMany(mappedBy = "project")
    private List<Student> students = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "panel_no") // Foreign key column in Project table referencing Panel
    private Panel panel;

    public boolean isFull() {
        return students != null && students.size() >= 4;
    }

    public boolean addStudent(Student student) {
        if (!isFull()) {
            students.add(student);
            return true;
        }
        return false;
    }
}
