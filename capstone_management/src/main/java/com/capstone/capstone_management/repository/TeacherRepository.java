package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Teacher findByUseraccount(UserAccount trn);
}
