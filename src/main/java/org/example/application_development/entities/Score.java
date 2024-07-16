package org.example.application_development.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @ToString.Exclude
    private Subject subject;

    public static Score toEntity(Map<String, Object> map) {
        Score score = new Score();
        score.setId((Long) map.get("id"));
        score.setScore((int) map.get("score"));
        score.setStudent(Student.toEntity((Map<String, Object>) map.get("student")));
        score.setSubject(Subject.toEntity((Map<String, Object>) map.get("subject")));
        return score;
    }

    public static Map<String, Object> toMap(Score score) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", score.getId());
        map.put("score", score.getScore());
        map.put("student", Student.toMap(score.getStudent()));
        map.put("subject", Subject.toMap(score.getSubject()));
        return map;
    }

    public static List<Score> toEntities(List<Map<String, Object>> scoreMaps) {
        List<Score> scores = new ArrayList<>();
        for (Map<String, Object> scoreMap : scoreMaps) {
            scores.add(Score.toEntity(scoreMap));
        }
        return scores;

    }

    public static Object toMaps(List<Score> scores) {
        List<Map<String, Object>> scoreMaps = new ArrayList<>();
        for (Score score : scores) {
            scoreMaps.add(Score.toMap(score));
        }
        return scoreMaps;

    }
}
