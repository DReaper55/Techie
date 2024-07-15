package org.example.application_development.controllers;

import org.example.application_development.entities.Score;
import org.example.application_development.entities.Student;
import org.example.application_development.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveScore(@RequestBody Map<String, Object> scoreMap) {
        Map<String, Object> response = new HashMap<>();

        try {
            Score score = Score.toEntity(scoreMap);
            scoreService.insert(score);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully saved score");

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to save score " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllScores() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Score> scores = scoreService.getAllScores();

            response.put("status", 200);
            response.put("success", true);
            response.put("message", scores.isEmpty()
                    ? "There are no saved scores"
                    : "Successfully retrieved scores");
            response.put("data", scores.stream().map(Score::toMap).collect(Collectors.toList()));

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get scores " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Map<String, Object>> getStudentScores(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Score> scores = scoreService.getAllStudentScores(id);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully retrieved scores");
            response.put("data", scores.stream().map(Score::toMap).collect(Collectors.toList()));

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get scores " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/median/{id}")
    public ResponseEntity<Map<String, Object>> getStudentMedianScore(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            double medianScore = scoreService.getStudentMedianScore(id);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully retrieved student score");
            response.put("data", medianScore);

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get student score " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/mode/{id}")
    public ResponseEntity<Map<String, Object>> getStudentModeScore(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Integer modeScore = scoreService.getStudentModeScore(id);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully retrieved student score");
            response.put("data", modeScore);

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get student score " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/mean/{id}")
    public ResponseEntity<Map<String, Object>> getStudentMeanScore(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            double meanScore = scoreService.getStudentMeanScore(id);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully retrieved student score");
            response.put("data", meanScore);

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get student " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
