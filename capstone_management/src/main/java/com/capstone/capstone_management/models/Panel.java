package com.capstone.capstone_management.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Panel {
    @Id
    private int panelno;
    private int panelsize;

    @OneToMany(mappedBy = "panel")
    private List<Teacher> teachers = new ArrayList<>();

    public boolean isFull() {
        return teachers != null && teachers.size() >= 3;
    }

    public boolean addTeacher(Teacher teacher) {
        if (!isFull()) {
            teachers.add(teacher);
            return true;
        }
        return false;
    }
}