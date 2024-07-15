package org.example.application_development.controllers;

import org.example.application_development.entities.Student;
import org.example.application_development.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Map<String, Object> studentMap) {
        Map<String, Object> response = new HashMap<>();

        try {
            Student student = Student.toEntity(studentMap);
            studentService.insert(student);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully created student");

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to create student " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Student> students = studentService.getAllStudents();

            response.put("status", 200);
            response.put("success", true);
            response.put("message", students.isEmpty()
                    ? "There are no saved students"
                    : "Successfully retrieved students");
            response.put("data", students.stream().map(Student::toMap).collect(Collectors.toList()));

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get students " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOneStudent(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Student student = studentService.getStudent(id);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully retrieved student");
            response.put("data", Student.toMap(student));

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get student " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
