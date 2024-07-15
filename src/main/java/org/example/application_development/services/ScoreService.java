package org.example.application_development.services;

import org.example.application_development.entities.Score;

import java.util.List;

public interface ScoreService {
    Long insert(Score score);
    Score getOneScore(Long scoreId);
    List<Score> getAllScores();
    List<Score> getAllStudentScores(Long studentId);
    List<Score> getAllSubjectScores(Long subjectId);
    double getStudentMeanScore(Long studentId);
    double getStudentMedianScore(Long studentId);
    Integer getStudentModeScore(Long studentId);
}
