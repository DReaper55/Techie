package org.example.application_development.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.application_development.entities.Subject;
import org.example.application_development.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SubjectServiceImplIntTest {

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setUp() {
        subjectRepository.deleteAll();
    }

    @Test
    public void testInsert() {
        Subject subject = new Subject();
        subject.setName("Math");

        Long id = subjectService.insert(subject);

        assertThat(id).isNotNull();

        Subject found = subjectRepository.findById(id).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Math");
    }

    @Test
    public void testGetSubject() {
        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Subject found = subjectService.getSubject(subject.getId());

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Math");
    }

    @Test
    public void testGetAllSubjects() {
        Subject subject1 = new Subject();
        subject1.setName("Math");

        Subject subject2 = new Subject();
        subject2.setName("Science");

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        List<Subject> subjects = subjectService.getAllSubjects();

        assertThat(subjects).hasSize(2).extracting(Subject::getName).containsExactlyInAnyOrder("Math", "Science");
    }
}
