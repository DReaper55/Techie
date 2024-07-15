package org.example.application_development.services;

import org.example.application_development.entities.Score;
import org.example.application_development.exceptions.ServiceException;
import org.example.application_development.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public Long insert(Score score) {
        try{
            Score savedScore = scoreRepository.save(score);
            return savedScore.getId();
        } catch (Exception e) {
            throw new ServiceException("Error saving score", e);
        }
    }

    @Override
    public Score getOneScore(Long scoreId) {
        return scoreRepository.findById(scoreId).orElse(null);
    }

    @Override
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    @Override
    public List<Score> getAllStudentScores(Long studentId) {
        try {
            return scoreRepository.findAll().stream()
                    .filter(score -> score.getStudent().getId().equals(studentId))
                    .toList();
        } catch (Exception e){
            throw new ServiceException("Error retrieving student's scores", e);
        }
    }

    @Override
    public List<Score> getAllSubjectScores(Long subjectId) {
        try {
            return scoreRepository.findAll().stream()
                    .filter(score -> score.getSubject().getId().equals(subjectId))
                    .toList();
        } catch (Exception e) {
            throw new ServiceException("Error finding scores of subject", e);
        }
    }

    @Override
    public double getStudentMeanScore(Long studentId) {
        try {
            return getAllStudentScores(studentId).stream().mapToInt(Score::getScore).average().orElse(0);
        } catch (Exception e) {
            throw new ServiceException("Error getting the Mean of this student's scores", e);
        }
    }

    @Override
    public double getStudentMedianScore(Long studentId) {
        try{
            List<Integer> sortedScores = getAllStudentScores(studentId).stream()
                    .map(Score::getScore)
                    .sorted()
                    .toList();
            int middle = sortedScores.size() / 2;
            if (sortedScores.size() % 2 == 0) {
                return (sortedScores.get(middle - 1) + sortedScores.get(middle)) / 2.0;
            } else {
                return sortedScores.get(middle);
            }
        } catch (Exception e){
            throw new ServiceException("Error getting the Median of this student's scores", e);
        }
    }

    @Override
    public Integer getStudentModeScore(Long studentId) {
        try {
            Map<Integer, Long> frequencyMap = getAllStudentScores(studentId).stream()
                    .collect(Collectors.groupingBy(Score::getScore, Collectors.counting()));
            return frequencyMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        } catch (Exception e) {
            throw new ServiceException("Error getting the Mode of this student's scores", e);
        }
    }
}
