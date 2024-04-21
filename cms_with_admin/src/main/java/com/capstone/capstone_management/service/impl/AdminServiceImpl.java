package com.capstone.capstone_management.service.impl;
import com.capstone.capstone_management.models.Review;
import com.capstone.capstone_management.models.Student;
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

    public AdminServiceImpl(UserAccountRepository userAccountRepository, PanelRepository panelRepository, ReviewRepository reviewRepository, ProjectRepository projectRepository, TimetableRepository timetableRepository,StudentRepository studentRepository) {
        this.userAccountRepository = userAccountRepository;
        this.panelRepository = panelRepository;
        this.reviewRepository = reviewRepository;
        this.projectRepository = projectRepository;
        this.timetableRepository = timetableRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public String getMarksCSV() {
        List<Student> students = studentRepository.findAll();
        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append("Student ID,Marks\n");

        for (Student student : students) {
            List<Review> studentReviews = reviewRepository.findByStudent(student);

            // Check if all review marks are present
            boolean allMarksPresent = studentReviews.stream()
                    .allMatch(review -> review.getEvalmark1() != 0 &&
                                        review.getEvalmark2() != 0 &&
                                        review.getEvalmark3() != 0 &&
                                        review.getEvalmark4() != 0);

            if (allMarksPresent) {
                int totalMarks = studentReviews.stream()
                        .mapToInt(review -> review.getEvalmark1() + review.getEvalmark2() + review.getEvalmark3() + review.getEvalmark4())
                        .sum();

                csvBuilder.append(student.getId()).append(",").append(totalMarks).append("\n");
            }
        }

        return csvBuilder.toString();
    }
}
