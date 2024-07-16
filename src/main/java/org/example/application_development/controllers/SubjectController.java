package org.example.application_development.controllers;

import org.example.application_development.entities.Subject;
import org.example.application_development.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSubject(@RequestBody Map<String, Object> subjectMap) {
        Map<String, Object> response = new HashMap<>();

        try {
            Subject subject = Subject.toEntity(subjectMap);
            subjectService.insert(subject);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully created subject");

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to create subject " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSubjects() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Subject> subjects = subjectService.getAllSubjects();

            response.put("status", 200);
            response.put("success", true);
            response.put("message", subjects.isEmpty()
                    ? "There are no saved subjects"
                    : "Successfully retrieved subjects");
            response.put("data", subjects.stream().map(Subject::toMap).toList());

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get subjects " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOneSubject(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Subject subject = subjectService.getSubject(id);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully retrieved subject");
            response.put("data", Subject.toMap(subject));

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get subject " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
