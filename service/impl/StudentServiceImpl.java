package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.models.Project;
import com.capstone.capstone_management.models.Student;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.ProjectRepository;
import com.capstone.capstone_management.repository.StudentRepository;
import com.capstone.capstone_management.repository.UserAccountRepository;
import com.capstone.capstone_management.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private UserAccountRepository userAccountRepository;
    private StudentRepository studentRepository;
    private ProjectRepository projectRepository;

    public StudentServiceImpl(UserAccountRepository userAccountRepository, StudentRepository studentRepository, ProjectRepository projectRepository) {
        this.userAccountRepository = userAccountRepository;
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Student findStudent(UserAccount userAccount){
        return studentRepository.findByUserAccount(userAccount);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public String GetStudentPassword(String SRN) {
        UserAccount u = userAccountRepository.findByUsn(SRN);
        Student t = studentRepository.findByUserAccount(u);
        UserAccount s = t.getUserAccount();
        return s.getPassword();
    }

    @Override
    public Project findProject(int teamid) {
        return projectRepository.findByTeamid(teamid);
    }

    @Override
    public Project getProject(String srn) {
        UserAccount userAccount = userAccountRepository.findByUsn(srn);
        Student stud = studentRepository.findByUserAccount(userAccount);
        List<Project> projects = projectRepository.findAll();
        for(Project p : projects){
            if(p.getStudents().contains(stud)){
                return p;
            }
        }
        return null;
    }

    public List<Student> getTeamMembersByStudentUSN(String usn) {
        Optional<Student> s = studentRepository.findByUserAccountUsn(usn);
        Project p = s.get().getProject();
        List<Student> st = studentRepository.findAllByProject(p);
        return st;
    }

    public List<Student> getStudentsByTeacherId(int teacherId) {
        return studentRepository.findByProjectTeacherId(teacherId);
    }
}
