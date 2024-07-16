package org.example.application_development.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Score> scores;

    public static Student toEntity(Map<String, Object> map) {
        Student student = new Student();
        if (map.containsKey("id")) {
            student.setId(((Number) map.get("id")).longValue());
        }
//        student.setId((Long) map.get("id"));
        student.setName((String) map.get("name"));
        return student;
    }

    public static Map<String, Object> toMap(Student student) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", student.getId());
        map.put("name", student.getName());
        return map;
    }

}
