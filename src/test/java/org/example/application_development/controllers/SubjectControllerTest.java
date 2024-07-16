package org.example.application_development.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application_development.entities.Subject;
import org.example.application_development.services.SubjectService;
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
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;


    @Test
    void testCreateSubject() throws Exception {
        // Mock request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Math");

        // Mock Subject object returned by service layer
        Subject mockedSubject = new Subject();
        mockedSubject.setId(1L);
        mockedSubject.setName("Math");

        // Mock service method behavior
        Mockito.when(subjectService.insert(Mockito.any(Subject.class))).thenReturn(mockedSubject.getId());

        // Perform POST request and verify response
        mockMvc.perform(post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testGetAllSubjects() throws Exception {
        // Mock Subject objects returned by service layer
        Subject subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("Math");

        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("Science");

        List<Subject> mockedSubjects = Arrays.asList(subject1, subject2);

        // Mock service method behavior
        Mockito.when(subjectService.getAllSubjects()).thenReturn(mockedSubjects);

        // Perform GET request and verify response
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].name").value("Math"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].name").value("Science"));
    }

    @Test
    void testGetOneSubject() throws Exception {
        // Mock Subject object returned by service layer
        Subject mockedSubject = new Subject();
        mockedSubject.setId(1L);
        mockedSubject.setName("Math");

        // Mock service method behavior
        Mockito.when(subjectService.getSubject(1L)).thenReturn(mockedSubject);

        // Perform GET request and verify response
        mockMvc.perform(get("/subjects/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Math"));
    }

}

