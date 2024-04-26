package com.capstone.capstone_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount useraccount;

    private String professionalrole;
    private int yearsofexp;
    private String domain;

    @ManyToOne
    @JoinColumn(name = "panel_no")
    private Panel panel;

    // Override hashCode() and equals() methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Teacher teacher = (Teacher) obj;
        return id == teacher.id &&
                yearsofexp == teacher.yearsofexp &&
                Objects.equals(useraccount, teacher.useraccount) &&
                Objects.equals(professionalrole, teacher.professionalrole) &&
                Objects.equals(domain, teacher.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, useraccount, professionalrole, yearsofexp, domain);
    }
}