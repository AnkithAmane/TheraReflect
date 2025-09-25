package com.example.mentalhealth.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mentalhealth.entity.Journal;
import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.repository.JournalRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates a new journal entry with AI analysis
     */
    public Journal createJournal(Patient patient, String content, Integer moodScore) {
        Journal journal = new Journal();
        journal.setPatient(patient);
        journal.setContent(content);
        journal.setMoodScore(moodScore);
        journal.setCreatedDate(LocalDateTime.now());

        // TODO: Replace with actual AI analysis when implemented
        performMockAIAnalysis(journal);

        return journalRepository.save(journal);
    }

    /**
     * Get all journals for a patient
     */
    public List<Journal> getJournalsByPatient(Patient patient) {
        return journalRepository.findByPatientOrderByCreatedDateDesc(patient);
    }

    /**
     * Get recent journals for AI analysis
     */
    public List<Journal> getRecentJournals(Patient patient, int limit) {
        return journalRepository.findByPatientOrderByCreatedDateDesc(patient)
                .stream().limit(limit).toList();
    }

    /**
     * Mock AI analysis (to be replaced with actual AI implementation)
     */
    private void performMockAIAnalysis(Journal journal) {
        String content = journal.getContent().toLowerCase();

        // Mock detected issues based on keywords
        List<String> detectedIssues = Arrays.asList();
        if (content.contains("sad") || content.contains("depressed")) {
            detectedIssues = Arrays.asList("Depression");
        } else if (content.contains("anxious") || content.contains("worry")) {
            detectedIssues = Arrays.asList("Anxiety");
        } else if (content.contains("stress")) {
            detectedIssues = Arrays.asList("Stress");
        }

        // Mock sentiment analysis
        double sentimentScore = 0.0;
        if (content.contains("happy") || content.contains("good") || content.contains("great")) {
            sentimentScore = 0.7;
        } else if (content.contains("sad") || content.contains("bad") || content.contains("terrible")) {
            sentimentScore = -0.7;
        }

        // Mock keywords extraction
        List<String> keywords = Arrays.asList("mood", "feelings", "day");

        try {
            journal.setDetectedIssues(objectMapper.writeValueAsString(detectedIssues));
            journal.setKeywords(objectMapper.writeValueAsString(keywords));
        } catch (JsonProcessingException e) {
            // Handle JSON serialization error
        }

        journal.setSentimentScore(sentimentScore);
        journal.setAiAnalysis("Mock analysis: " + detectedIssues.toString());
    }
}
