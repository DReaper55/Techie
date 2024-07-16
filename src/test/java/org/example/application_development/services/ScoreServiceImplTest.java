package org.example.application_development.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.example.application_development.entities.Score;
import org.example.application_development.entities.Student;
import org.example.application_development.entities.Subject;
import org.example.application_development.repository.ScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreServiceImplTest {

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreServiceImpl scoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        Score score = new Score();
        score.setId(1L);
        score.setScore(100);

        when(scoreRepository.save(any(Score.class))).thenReturn(score);

        Long id = scoreService.insert(score);

        assertNotNull(id);
        assertEquals(1L, id);
        verify(scoreRepository, times(1)).save(score);
    }

    @Test
    void testGetOneScore() {
        Score score = new Score();
        score.setId(1L);
        score.setScore(100);

        when(scoreRepository.findById(1L)).thenReturn(Optional.of(score));

        Score foundScore = scoreService.getOneScore(1L);

        assertNotNull(foundScore);
        assertEquals(100, foundScore.getScore());
        verify(scoreRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllScores() {
        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(100);

        Score score2 = new Score();
        score2.setId(2L);
        score2.setScore(90);

        List<Score> scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);

        when(scoreRepository.findAll()).thenReturn(scores);

        List<Score> foundScores = scoreService.getAllScores();

        assertNotNull(foundScores);
        assertEquals(2, foundScores.size());
        verify(scoreRepository, times(1)).findAll();
    }

    @Test
    void testGetAllStudentScores() {
        Student student = new Student();
        student.setId(1L);

        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(100);
        score1.setStudent(student);

        Score score2 = new Score();
        score2.setId(2L);
        score2.setScore(90);
        score2.setStudent(student);

        List<Score> scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);

        when(scoreRepository.findAll()).thenReturn(scores);

        List<Score> foundScores = scoreService.getAllStudentScores(1L);

        assertNotNull(foundScores);
        assertEquals(2, foundScores.size());
        verify(scoreRepository, times(1)).findAll();
    }

    @Test
    void testGetAllSubjectScores() {
        Subject subject = new Subject();
        subject.setId(1L);

        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(100);
        score1.setSubject(subject);

        Score score2 = new Score();
        score2.setId(2L);
        score2.setScore(90);
        score2.setSubject(subject);

        List<Score> scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);

        when(scoreRepository.findAll()).thenReturn(scores);

        List<Score> foundScores = scoreService.getAllSubjectScores(1L);

        assertNotNull(foundScores);
        assertEquals(2, foundScores.size());
        verify(scoreRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentMeanScore() {
        Student student = new Student();
        student.setId(1L);

        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(100);
        score1.setStudent(student);

        Score score2 = new Score();
        score2.setId(2L);
        score2.setScore(90);
        score2.setStudent(student);

        List<Score> scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);

        when(scoreRepository.findAll()).thenReturn(scores);

        double mean = scoreService.getStudentMeanScore(1L);

        assertEquals(95.0, mean);
    }

    @Test
    void testGetStudentMedianScore() {
        Student student = new Student();
        student.setId(1L);

        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(100);
        score1.setStudent(student);

        Score score2 = new Score();
        score2.setId(2L);
        score2.setScore(90);
        score2.setStudent(student);

        List<Score> scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);

        when(scoreRepository.findAll()).thenReturn(scores);

        double median = scoreService.getStudentMedianScore(1L);

        assertEquals(95.0, median);
    }

    @Test
    void testGetStudentModeScore() {
        Student student = new Student();
        student.setId(1L);

        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(100);
        score1.setStudent(student);

        Score score2 = new Score();
        score2.setId(2L);
        score2.setScore(90);
        score2.setStudent(student);

        Score score3 = new Score();
        score3.setId(3L);
        score3.setScore(90);
        score3.setStudent(student);

        List<Score> scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);

        when(scoreRepository.findAll()).thenReturn(scores);

        int mode = scoreService.getStudentModeScore(1L);

        assertEquals(90, mode);
    }
}

