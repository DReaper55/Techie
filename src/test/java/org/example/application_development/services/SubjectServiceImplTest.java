package org.example.application_development.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.example.application_development.entities.Subject;
import org.example.application_development.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");

        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Long id = subjectService.insert(subject);

        assertNotNull(id);
        assertEquals(1L, id);
        verify(subjectRepository, times(1)).save(subject);
    }

    @Test
    void testGetSubject() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Subject foundSubject = subjectService.getSubject(1L);

        assertNotNull(foundSubject);
        assertEquals("Math", foundSubject.getName());
        verify(subjectRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllSubjects() {
        Subject subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("Math");

        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("Science");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);

        when(subjectRepository.findAll()).thenReturn(subjects);

        List<Subject> foundSubjects = subjectService.getAllSubjects();

        assertNotNull(foundSubjects);
        assertEquals(2, foundSubjects.size());
        verify(subjectRepository, times(1)).findAll();
    }
}
