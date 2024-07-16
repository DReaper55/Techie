package org.example.application_development.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application_development.entities.Score;
import org.example.application_development.entities.Student;
import org.example.application_development.entities.Subject;
import org.example.application_development.services.ScoreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScoreService scoreService;

    @Test
    void testSaveScore() throws Exception {
        // Mock scoreMap request body
        Map<String, Object> scoreMap = new HashMap<>();
        scoreMap.put("score", 85);
        Map<String, Object> studentMap = new HashMap<>();
        studentMap.put("id", 1L);
        scoreMap.put("student", studentMap);
        Map<String, Object> subjectMap = new HashMap<>();
        subjectMap.put("id", 1L);
        scoreMap.put("subject", subjectMap);

        // Mock service method behavior
        Mockito.when(scoreService.insert(Mockito.any(Score.class))).thenReturn(1L); // Assuming ID 1 is returned

        // Perform POST request and verify response
        mockMvc.perform(post("/scores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(scoreMap)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testGetAllScores() throws Exception {
        // Mock Score objects returned by service layer
        Score score1 = new Score();
        score1.setId(1L);
        score1.setScore(85);
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Jermaine Cole");
        score1.setStudent(student1);
        Subject subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("Math");
        score1.setSubject(subject1);

        Score score2 = new Score();
        score2.setId(2L);
        score2.setScore(75);
        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        score2.setStudent(student2);
        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("Science");
        score2.setSubject(subject2);

        List<Score> mockedScores = Arrays.asList(score1, score2);

        // Mock service method behavior
        Mockito.when(scoreService.getAllScores()).thenReturn(mockedScores);

        // Perform GET request and verify response
        mockMvc.perform(get("/scores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].score").value(85))
                .andExpect(jsonPath("$.data[0].student.id").value(1L))
                .andExpect(jsonPath("$.data[0].student.name").value("Jermaine Cole"))
                .andExpect(jsonPath("$.data[0].subject.id").value(1L))
                .andExpect(jsonPath("$.data[0].subject.name").value("Math"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].score").value(75))
                .andExpect(jsonPath("$.data[1].student.id").value(2L))
                .andExpect(jsonPath("$.data[1].student.name").value("Jane Smith"))
                .andExpect(jsonPath("$.data[1].subject.id").value(2L))
                .andExpect(jsonPath("$.data[1].subject.name").value("Science"));
    }

    @Test
    void testGetStudentMedianScore() throws Exception {
        Long studentId = 1L;
        double medianScore = 85.0;

        // Mock service method behavior
        Mockito.when(scoreService.getStudentMedianScore(studentId)).thenReturn(medianScore);

        // Perform GET request and verify response
        mockMvc.perform(get("/scores/median/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Successfully retrieved student score"))
                .andExpect(jsonPath("$.data").value(medianScore));
    }

    @Test
    void testGetStudentModeScore() throws Exception {
        Long studentId = 1L;
        Integer modeScore = 85;

        // Mock service method behavior
        Mockito.when(scoreService.getStudentModeScore(studentId)).thenReturn(modeScore);

        // Perform GET request and verify response
        mockMvc.perform(get("/scores/mode/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Successfully retrieved student score"))
                .andExpect(jsonPath("$.data").value(modeScore));
    }

    @Test
    void testGetStudentMeanScore() throws Exception {
        Long studentId = 1L;
        double meanScore = 80.0;

        // Mock service method behavior
        Mockito.when(scoreService.getStudentMeanScore(studentId)).thenReturn(meanScore);

        // Perform GET request and verify response
        mockMvc.perform(get("/scores/mean/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Successfully retrieved student score"))
                .andExpect(jsonPath("$.data").value(meanScore));
    }
}

