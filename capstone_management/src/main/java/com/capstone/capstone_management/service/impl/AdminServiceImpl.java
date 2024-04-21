package com.capstone.capstone_management.service.impl;
import com.capstone.capstone_management.models.Panel;
import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.Teacher;

import com.capstone.capstone_management.repository.*;
import com.capstone.capstone_management.service.AdminService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private UserAccountRepository userAccountRepository;
    private PanelRepository panelRepository;
    private ReviewRepository reviewRepository;
    private ProjectRepository projectRepository;
    private TimetableRepository timetableRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public AdminServiceImpl(UserAccountRepository userAccountRepository, PanelRepository panelRepository, ReviewRepository reviewRepository, ProjectRepository projectRepository, TimetableRepository timetableRepository,StudentRepository studentRepository, TeacherRepository tea) {
        this.userAccountRepository = userAccountRepository;
        this.panelRepository = panelRepository;
        this.reviewRepository = reviewRepository;
        this.projectRepository = projectRepository;
        this.timetableRepository = timetableRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public String getMarksCSV() {
        List<Student> students = studentRepository.findAll();
        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append("Student ID,Marks,Grade\n");

        for (Student student : students) {
            List<Review> studentReviews = reviewRepository.findByStudent(student);

            boolean allMarksPresent = studentReviews.stream()
                    .allMatch(review -> review.getEvalmark1() != 0 &&
                            review.getEvalmark2() != 0 &&
                            review.getEvalmark3() != 0 &&
                            review.getEvalmark4() != 0);

            if (allMarksPresent) {
                int totalMarks = studentReviews.stream()
                        .mapToInt(review -> review.getEvalmark1() + review.getEvalmark2() + review.getEvalmark3() + review.getEvalmark4())
                        .sum();

                double averageGrade = (double) totalMarks / 4.0;
                String grade = getGrade(averageGrade);

                csvBuilder.append(student.getId())
                        .append(",")
                        .append(totalMarks)
                        .append(",")
                        .append(grade)
                        .append("\n");
            }
        }

        return csvBuilder.toString();
    }

    private String getGrade(double averageGrade) {
        if (averageGrade >= 9.0) {
            return "S";
        } else if (averageGrade >= 8.0) {
            return "A";
        } else if (averageGrade >= 7.0) {
            return "B";
        } else if (averageGrade >= 6.0) {
            return "C";
        } else if (averageGrade >= 5.0) {
            return "D";
        } else if (averageGrade >= 4.0) {
            return "E";
        } else {
            return "F";
        }
    }
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void savePanel(Panel panel) {
        panelRepository.save(panel);
    }
}
