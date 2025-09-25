package com.example.mentalhealth.service;

import java.util.List;

import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.entity.TherapistRecommendation;

/**
 * Service interface for AI-powered mental health analysis This will be
 * implemented with actual NLP/AI integration later
 */
public interface AIAnalysisService {

    /**
     * Analyzes journal content using NLP to detect mental health issues
     *
     * @param journalContent The text content to analyze
     * @return Analysis result containing detected issues, sentiment, keywords
     */
    JournalAnalysisResult analyzeJournal(String journalContent);

    /**
     * Generates therapist recommendations based on patient's journal history
     *
     * @param patient The patient for whom to generate recommendations
     * @return List of recommended therapists with scores and reasoning
     */
    List<TherapistRecommendation> generateTherapistRecommendations(Patient patient);

    /**
     * Creates a summary of patient's mental health based on journal entries
     *
     * @param patient The patient to analyze
     * @return AI-generated summary for therapist review
     */
    String generatePatientSummary(Patient patient);

    /**
     * Data class for journal analysis results
     */
    public static class JournalAnalysisResult {

        private List<String> detectedIssues;
        private Double sentimentScore;
        private List<String> keywords;
        private Integer moodScore;
        private String analysis;

        // Constructors, getters, setters
        public JournalAnalysisResult() {
        }

        public JournalAnalysisResult(List<String> detectedIssues, Double sentimentScore,
                List<String> keywords, Integer moodScore, String analysis) {
            this.detectedIssues = detectedIssues;
            this.sentimentScore = sentimentScore;
            this.keywords = keywords;
            this.moodScore = moodScore;
            this.analysis = analysis;
        }

        // Getters and Setters
        public List<String> getDetectedIssues() {
            return detectedIssues;
        }

        public void setDetectedIssues(List<String> detectedIssues) {
            this.detectedIssues = detectedIssues;
        }

        public Double getSentimentScore() {
            return sentimentScore;
        }

        public void setSentimentScore(Double sentimentScore) {
            this.sentimentScore = sentimentScore;
        }

        public List<String> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<String> keywords) {
            this.keywords = keywords;
        }

        public Integer getMoodScore() {
            return moodScore;
        }

        public void setMoodScore(Integer moodScore) {
            this.moodScore = moodScore;
        }

        public String getAnalysis() {
            return analysis;
        }

        public void setAnalysis(String analysis) {
            this.analysis = analysis;
        }
    }
}
