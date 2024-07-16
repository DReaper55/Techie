package org.example.application_development.services;

import org.example.application_development.entities.Subject;
import org.example.application_development.exceptions.ServiceException;
import org.example.application_development.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Long insert(Subject subject) {
        try{
            Subject savedSubject = subjectRepository.save(subject);
            return savedSubject.getId();
        } catch (Exception e) {
            throw new ServiceException("Error saving subject", e);
        }
    }

    @Override
    public Subject getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
