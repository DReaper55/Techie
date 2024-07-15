package org.example.application_development.services;

import org.example.application_development.entities.Subject;

import java.util.List;

public interface SubjectService {
    Long insert(Subject subject);
    Subject getSubject(Long subjectId);
    List<Subject> getAllSubjects();
}
