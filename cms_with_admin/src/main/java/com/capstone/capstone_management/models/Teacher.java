package com.capstone.capstone_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "panel_no") // Foreign key column in Teacher table referencing Panel
    private Panel panel;
}
