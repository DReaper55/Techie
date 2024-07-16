package org.example.application_development.services;

import org.example.application_development.entities.Report;
import org.example.application_development.entities.Score;
import org.example.application_development.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScoreService scoreService;

    @Override
    public Report getReport(Long studentId) {
        Student student = studentService.getStudent(studentId);
        List<Score> scores = scoreService.getAllStudentScores(studentId);
        double meanScore = scoreService.getStudentMeanScore(studentId);
        Integer modeScore = scoreService.getStudentModeScore(studentId);
        double medianScore = scoreService.getStudentMedianScore(studentId);

        return new Report(student.getName(), scores, meanScore, modeScore, medianScore);
    }

    @Override
    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        List<Student> students = studentService.getAllStudents();

        for(Student student : students){
            reports.add(getReport(student.getId()));
        }

        return reports;
    }
}
