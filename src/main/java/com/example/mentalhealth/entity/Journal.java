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
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "mood_score")
    private Integer moodScore; // 1-10 scale

    @Column(name = "detected_issues", columnDefinition = "TEXT")
    private String detectedIssues; // JSON array of detected mental health issues

    @Column(name = "ai_analysis", columnDefinition = "TEXT")
    private String aiAnalysis; // AI-generated analysis

    @Column(name = "sentiment_score")
    private Double sentimentScore; // -1.0 to 1.0

    @Column(name = "keywords", columnDefinition = "TEXT")
    private String keywords; // JSON array of extracted keywords

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
