package org.example.application_development.controllers;

import org.example.application_development.entities.Report;
import org.example.application_development.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllReports() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Report> reports = reportService.getAllReports();

            response.put("status", 200);
            response.put("success", true);
            response.put("message", reports.isEmpty()
                    ? "There are no saved reports"
                    : "Successfully retrieved reports");
            response.put("data", reports.stream().map(Report::toMap).toList());

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get reports " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getReportReports(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Report report = reportService.getReport(id);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Successfully retrieved report");
            response.put("data", Report.toMap(report));

        } catch (Exception e){
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Failed to get report " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
