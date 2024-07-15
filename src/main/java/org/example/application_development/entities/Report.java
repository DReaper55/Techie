package org.example.application_development.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Report {
    private String studentName;
    private List<Score> scores;
    private double meanScore;
    private int modeScore;
    private double medianScore;

    public Report() {}

    public Report(String studentName, List<Score> scores, double meanScore, int modeScore, double medianScore) {
        this.studentName = studentName;
        this.scores = scores;
        this.meanScore = meanScore;
        this.modeScore = modeScore;
        this.medianScore = medianScore;
    }

    public static Report toEntity(Map<String, Object> map) {
        Report report = new Report();
        report.setStudentName((String) map.get("studentName"));

        List<Map<String, Object>> scoreMaps = new ArrayList<>();
        Object scoresObject = map.get("scores");
        if (scoresObject instanceof List) {
            scoreMaps = (List<Map<String, Object>>) scoresObject;
        }


        report.setScores(Score.toEntities(scoreMaps));
        report.setMeanScore((double) map.get("meanScore"));
        report.setModeScore((int) map.get("modeScore"));
        report.setMedianScore((double) map.get("medianScore"));
        return report;
    }

    public static Map<String, Object> toMap(Report report) {
        Map<String, Object> map = new HashMap<>();
        map.put("studentName", report.getStudentName());
         map.put("scores", Score.toMaps(report.getScores()));
        map.put("meanScore", report.getMeanScore());
        map.put("modeScore", report.getModeScore());
        map.put("medianScore", report.getMedianScore());
        return map;
    }

}
