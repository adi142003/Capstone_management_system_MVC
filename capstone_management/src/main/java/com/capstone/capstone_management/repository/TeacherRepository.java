package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Teacher;
import com.capstone.capstone_management.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Teacher findByUseraccount(UserAccount trn);
    Optional<Teacher> findById(Long id);
}
