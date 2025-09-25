package com.example.mentalhealth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "therapist_recommendations")
public class TherapistRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @Column(name = "recommendation_score")
    private Double recommendationScore; // 0.0 to 1.0

    @Column(name = "matching_specialties", columnDefinition = "TEXT")
    private String matchingSpecialties; // JSON array of matching specialties

    @Column(name = "ai_reasoning", columnDefinition = "TEXT")
    private String aiReasoning; // AI explanation for recommendation

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }
}
