package org.example.application_development.services;

import org.example.application_development.entities.Student;

import java.util.List;

public interface StudentService {
    Long insert(Student student);
    Student getStudent(Long studentId);
    List<Student> getAllStudents();
}
