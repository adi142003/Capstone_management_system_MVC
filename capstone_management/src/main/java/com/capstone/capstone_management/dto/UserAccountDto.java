package com.capstone.capstone_management.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserAccountDto {
    private UUID UserID;
    private String Name;
    private String Gender;
    private String Role;
    private String Department;
}
