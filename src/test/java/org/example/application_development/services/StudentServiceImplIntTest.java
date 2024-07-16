package org.example.application_development.services;

import org.example.application_development.entities.Student;
import org.example.application_development.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentServiceImplIntTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setName("John Doe");

        Long id = studentService.insert(student);

        assertThat(id).isNotNull();

        Student found = studentRepository.findById(id).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("John Doe");
    }

    @Test
    public void testGetStudent() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Student found = studentService.getStudent(student.getId());

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("John Doe");
    }

    @Test
    public void testGetAllStudents() {
        Student student1 = new Student();
        student1.setName("John Doe");

        Student student2 = new Student();
        student2.setName("Jane Smith");

        studentRepository.save(student1);
        studentRepository.save(student2);

        List<Student> students = studentService.getAllStudents();

        assertThat(students).hasSize(2).extracting(Student::getName).containsExactlyInAnyOrder("John Doe", "Jane Smith");
    }
}

