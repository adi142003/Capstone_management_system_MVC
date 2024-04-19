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
    private String TRN;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
    private String ProfessionalRole;
    private int YearsofExp;
    private String Domain;
    @ManyToOne
    @JoinColumn(name = "panel_no") // Foreign key column in Teacher table referencing Panel
    private Panel panel;
}
