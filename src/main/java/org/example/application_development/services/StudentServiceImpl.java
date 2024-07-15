package org.example.application_development.services;

import org.example.application_development.entities.Student;
import org.example.application_development.exceptions.ServiceException;
import org.example.application_development.repository.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Long insert(Student student) {
        try{
            Student savedStudent = studentRepository.save(student);
            return savedStudent.getId();
        } catch (Exception e) {
            throw new ServiceException("Error saving student", e);
        }
    }

    @Override
    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
