package org.example.application_development.entities;

import lombok.Data;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores;

    public static Subject toEntity(Map<String, Object> map) {
        Subject subject = new Subject();
        subject.setId((Long) map.get("id"));
        subject.setName((String) map.get("name"));
        List<Map<String, Object>> scoreMaps = (List<Map<String, Object>>) map.get("scores");
         subject.setScores(Score.toEntities(scoreMaps));
        return subject;
    }

    public static Map<String, Object> toMap(Subject subject) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", subject.getId());
        map.put("name", subject.getName());
         map.put("scores", Score.toMaps(subject.getScores()));
        return map;
    }

}
