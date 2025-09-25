package com.example.mentalhealth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mentalhealth.entity.Journal;
import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.entity.PatientTherapistMatch;
import com.example.mentalhealth.entity.Therapist;
import com.example.mentalhealth.entity.TherapistRecommendation;
import com.example.mentalhealth.repository.JournalRepository;
import com.example.mentalhealth.repository.PatientTherapistMatchRepository;
import com.example.mentalhealth.repository.TherapistRecommendationRepository;
import com.example.mentalhealth.repository.TherapistRepository;

@Service
public class TherapistRecommendationService {

    @Autowired
    private TherapistRecommendationRepository recommendationRepository;

    @Autowired
    private TherapistRepository therapistRepository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private PatientTherapistMatchRepository matchRepository;

    /**
     * Generate therapist recommendations for a patient based on their journal
     * entries This is a mock implementation - will be replaced with actual AI
     * logic
     */
    public List<TherapistRecommendation> generateRecommendations(Patient patient) {
        List<TherapistRecommendation> recommendations = new ArrayList<>();

        // Get patient's recent journals for analysis
        List<Journal> recentJournals = journalRepository.findByPatientOrderByCreatedDateDesc(patient)
                .stream().limit(10).toList();

        // Mock analysis of detected issues
        List<String> patientIssues = extractIssuesFromJournals(recentJournals);

        // Get all available therapists
        List<Therapist> availableTherapists = therapistRepository.findByIsAvailable(true);

        // Score each therapist based on specialty match (mock implementation)
        for (Therapist therapist : availableTherapists) {
            double score = calculateMatchScore(patientIssues, therapist);
            if (score > 0.3) { // Only recommend if reasonable match
                TherapistRecommendation recommendation = new TherapistRecommendation();
                recommendation.setPatient(patient);
                recommendation.setTherapist(therapist);
                recommendation.setRecommendationScore(score);
                recommendation.setMatchingSpecialties(String.join(",", patientIssues));
                recommendation.setAiReasoning(generateMockReasoning(patientIssues, therapist, score));

                recommendations.add(recommendationRepository.save(recommendation));
            }
        }

        return recommendations;
    }

    /**
     * Get existing recommendations for a patient
     */
    public List<TherapistRecommendation> getRecommendationsForPatient(Patient patient) {
        return recommendationRepository.findByPatientOrderByRecommendationScoreDesc(patient);
    }

    /**
     * Create a patient-therapist match when patient selects a therapist
     */
    public PatientTherapistMatch createMatch(Patient patient, Therapist therapist) {
        PatientTherapistMatch match = new PatientTherapistMatch();
        match.setPatient(patient);
        match.setTherapist(therapist);
        match.setStatus(PatientTherapistMatch.MatchStatus.PENDING);
        match.setPatientSummary(generatePatientSummary(patient));

        return matchRepository.save(match);
    }

    /**
     * Generate patient summary for therapist (mock implementation)
     */
    public String generatePatientSummary(Patient patient) {
        List<Journal> journals = journalRepository.findByPatientOrderByCreatedDateDesc(patient)
                .stream().limit(20).toList();

        if (journals.isEmpty()) {
            return "No journal entries available for analysis.";
        }

        StringBuilder summary = new StringBuilder();
        summary.append("Patient Summary for ").append(patient.getName()).append(":\n\n");
        summary.append("Total Journal Entries: ").append(journals.size()).append("\n");

        List<String> detectedIssues = extractIssuesFromJournals(journals);
        if (!detectedIssues.isEmpty()) {
            summary.append("Detected Mental Health Concerns: ").append(String.join(", ", detectedIssues)).append("\n");
        }

        summary.append("\nRecent Patterns:\n");
        summary.append("- Patient has been actively journaling\n");
        summary.append("- Shows engagement with mental health self-care\n");

        return summary.toString();
    }

    /**
     * Mock method to extract issues from journals
     */
    private List<String> extractIssuesFromJournals(List<Journal> journals) {
        List<String> issues = new ArrayList<>();

        for (Journal journal : journals) {
            String content = (journal.getContent() != null) ? journal.getContent().toLowerCase() : "";

            if (content.contains("anxiety") || content.contains("anxious") || content.contains("worry")) {
                if (!issues.contains("Anxiety")) {
                    issues.add("Anxiety");
                }
            }
            if (content.contains("depression") || content.contains("depressed") || content.contains("sad")) {
                if (!issues.contains("Depression")) {
                    issues.add("Depression");
                }
            }
            if (content.contains("stress") || content.contains("stressed")) {
                if (!issues.contains("Stress")) {
                    issues.add("Stress");
                }
            }
        }

        return issues;
    }

    /**
     * Mock method to calculate match score between patient issues and therapist
     */
    private double calculateMatchScore(List<String> patientIssues, Therapist therapist) {
        if (patientIssues.isEmpty()) {
            return 0.5; // Base score for no specific issues
        }
        String therapistSpecialty = (therapist.getSpecialty() != null)
                ? therapist.getSpecialty().toLowerCase() : "";

        double score = 0.3; // Base score

        for (String issue : patientIssues) {
            if (therapistSpecialty.contains(issue.toLowerCase())) {
                score += 0.3; // Add points for specialty match
            }
        }

        return Math.min(score, 1.0); // Cap at 1.0
    }

    /**
     * Generate mock reasoning for recommendation
     */
    private String generateMockReasoning(List<String> issues, Therapist therapist, double score) {
        StringBuilder reasoning = new StringBuilder();
        reasoning.append("Recommended based on: ");

        if (!issues.isEmpty()) {
            reasoning.append("Patient's identified concerns (")
                    .append(String.join(", ", issues))
                    .append(") align with therapist's specialty in ")
                    .append(therapist.getSpecialty()).append(". ");
        }

        reasoning.append("Match confidence: ")
                .append(Math.round(score * 100))
                .append("%");

        return reasoning.toString();
    }
}
