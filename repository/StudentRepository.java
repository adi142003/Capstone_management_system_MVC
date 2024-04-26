package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUserAccount(UserAccount userAccount);
    Student findById(int student_id);
    Optional<Student> findByUserAccountUsn(String usn);
    List<Student> findByProjectTeacherId(int teacherId);

    List<Student> findAllByProject(Project p);
}
