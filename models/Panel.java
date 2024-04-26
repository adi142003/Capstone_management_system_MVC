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
import java.util.Objects;

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

    // Override hashCode() and equals() methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Panel panel = (Panel) obj;
        return panelno == panel.panelno && panelsize == panel.panelsize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(panelno, panelsize);
    }

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