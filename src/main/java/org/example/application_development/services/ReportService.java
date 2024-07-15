package org.example.application_development.services;

import org.example.application_development.entities.Report;

import java.util.List;

public interface ReportService {
    Report getReport(Long studentId);
    List<Report> getAllReports();
}
