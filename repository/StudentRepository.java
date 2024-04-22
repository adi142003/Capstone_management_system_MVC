package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.UserAccount;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByUserAccount(UserAccount userAccount);
    Student findById(int student_id);
    Optional<Student> findByUserAccountUsn(String usn);
    List<Student> findByProjectTeacherId(int teacherId);
}
