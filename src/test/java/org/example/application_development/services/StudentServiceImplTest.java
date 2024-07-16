package org.example.application_development.services;

import org.example.application_development.entities.Student;
import org.example.application_development.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Smith");

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Long id = studentService.insert(student);

        assertNotNull(id);
        assertEquals(1L, id);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Smith");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudent(1L);

        assertNotNull(foundStudent);
        assertEquals("John Smith", foundStudent.getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllStudents() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Smith");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> foundStudents = studentService.getAllStudents();

        assertNotNull(foundStudents);
        assertEquals(2, foundStudents.size());
        verify(studentRepository, times(1)).findAll();
    }
}

