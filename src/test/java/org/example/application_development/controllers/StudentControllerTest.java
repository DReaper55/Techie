package org.example.application_development.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application_development.entities.Student;
import org.example.application_development.services.StudentService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;


    @Test
    void testCreateStudent() throws Exception {
        // Mock request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe");

        // Mock Student object returned by service layer
        Student mockedStudent = new Student();
        mockedStudent.setId(1L);
        mockedStudent.setName("John Doe");

        // Mock service method behavior
        Mockito.when(studentService.insert(Mockito.any(Student.class))).thenReturn(mockedStudent.getId());

        // Perform POST request and verify response
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testGetAllStudents() throws Exception {
        // Mock Student objects returned by service layer
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");

        List<Student> mockedStudents = Arrays.asList(student1, student2);

        // Mock service method behavior
        Mockito.when(studentService.getAllStudents()).thenReturn(mockedStudents);

        // Perform GET request and verify response
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].name").value("John Doe"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].name").value("Jane Smith"));
    }

    @Test
    void testGetOneStudent() throws Exception {
        // Mock Student object returned by service layer
        Student mockedStudent = new Student();
        mockedStudent.setId(1L);
        mockedStudent.setName("John Doe");

        // Mock service method behavior
        Mockito.when(studentService.getStudent(1L)).thenReturn(mockedStudent);

        // Perform GET request and verify response
        mockMvc.perform(get("/students/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Successfully retrieved student"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("John Doe"));
    }
}

