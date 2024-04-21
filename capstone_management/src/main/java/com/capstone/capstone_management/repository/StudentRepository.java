package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByUserAccount(UserAccount userAccount);
}
