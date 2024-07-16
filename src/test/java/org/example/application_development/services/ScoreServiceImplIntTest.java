package org.example.application_development.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.application_development.entities.Score;
import org.example.application_development.entities.Student;
import org.example.application_development.entities.Subject;
import org.example.application_development.repository.ScoreRepository;
import org.example.application_development.repository.StudentRepository;
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
public class ScoreServiceImplIntTest {

    @Autowired
    private ScoreServiceImpl scoreService;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setUp() {
        scoreRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();
    }

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score = new Score();
        score.setScore(100);
        score.setStudent(student);
        score.setSubject(subject);

        Long id = scoreService.insert(score);

        assertThat(id).isNotNull();

        Score found = scoreRepository.findById(id).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getScore()).isEqualTo(100);
    }

    @Test
    public void testGetOneScore() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score = new Score();
        score.setScore(100);
        score.setStudent(student);
        score.setSubject(subject);
        scoreRepository.save(score);

        Score found = scoreService.getOneScore(score.getId());

        assertThat(found).isNotNull();
        assertThat(found.getScore()).isEqualTo(100);
    }

    @Test
    public void testGetAllScores() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score1 = new Score();
        score1.setScore(100);
        score1.setStudent(student);
        score1.setSubject(subject);

        Score score2 = new Score();
        score2.setScore(90);
        score2.setStudent(student);
        score2.setSubject(subject);

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        List<Score> scores = scoreService.getAllScores();

        assertThat(scores).hasSize(2).extracting(Score::getScore).containsExactlyInAnyOrder(100, 90);
    }

    @Test
    public void testGetAllStudentScores() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score1 = new Score();
        score1.setScore(100);
        score1.setStudent(student);
        score1.setSubject(subject);

        Score score2 = new Score();
        score2.setScore(90);
        score2.setStudent(student);
        score2.setSubject(subject);

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        List<Score> scores = scoreService.getAllStudentScores(student.getId());

        assertThat(scores).hasSize(2).extracting(Score::getScore).containsExactlyInAnyOrder(100, 90);
    }

    @Test
    public void testGetAllSubjectScores() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score1 = new Score();
        score1.setScore(100);
        score1.setStudent(student);
        score1.setSubject(subject);

        Score score2 = new Score();
        score2.setScore(90);
        score2.setStudent(student);
        score2.setSubject(subject);

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        List<Score> scores = scoreService.getAllSubjectScores(subject.getId());

        assertThat(scores).hasSize(2).extracting(Score::getScore).containsExactlyInAnyOrder(100, 90);
    }

    @Test
    public void testGetStudentMeanScore() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score1 = new Score();
        score1.setScore(100);
        score1.setStudent(student);
        score1.setSubject(subject);

        Score score2 = new Score();
        score2.setScore(90);
        score2.setStudent(student);
        score2.setSubject(subject);

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        double mean = scoreService.getStudentMeanScore(student.getId());

        assertThat(mean).isEqualTo(95.0);
    }

    @Test
    public void testGetStudentMedianScore() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score1 = new Score();
        score1.setScore(100);
        score1.setStudent(student);
        score1.setSubject(subject);

        Score score2 = new Score();
        score2.setScore(90);
        score2.setStudent(student);
        score2.setSubject(subject);

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        double median = scoreService.getStudentMedianScore(student.getId());

        assertThat(median).isEqualTo(95.0);
    }

    @Test
    public void testGetStudentModeScore() {
        Student student = new Student();
        student.setName("John Doe");
        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math");
        subjectRepository.save(subject);

        Score score1 = new Score();
        score1.setScore(100);
        score1.setStudent(student);
        score1.setSubject(subject);

        Score score2 = new Score();
        score2.setScore(90);
        score2.setStudent(student);
        score2.setSubject(subject);

        Score score3 = new Score();
        score3.setScore(90);
        score3.setStudent(student);
        score3.setSubject(subject);

        scoreRepository.save(score1);
        scoreRepository.save(score2);
        scoreRepository.save(score3);

        Integer mode = scoreService.getStudentModeScore(student.getId());

        assertThat(mode).isEqualTo(90);
    }
}

