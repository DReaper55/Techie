package org.example.application_development.entities;

import lombok.Data;
import jakarta.persistence.*;
import lombok.ToString;

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
    @ToString.Exclude
    private List<Score> scores;

    public static Subject toEntity(Map<String, Object> map) {
        Subject subject = new Subject();
//        subject.setId((Long) map.get("id"));
        if (map.containsKey("id")) {
            subject.setId(((Number) map.get("id")).longValue());
        }
        subject.setName((String) map.get("name"));
        return subject;
    }

    public static Map<String, Object> toMap(Subject subject) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", subject.getId());
        map.put("name", subject.getName());
        return map;
    }

}
