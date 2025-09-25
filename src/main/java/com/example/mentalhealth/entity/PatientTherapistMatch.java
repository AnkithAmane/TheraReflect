package com.example.mentalhealth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "patient_therapist_matches")
public class PatientTherapistMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @Enumerated(EnumType.STRING)
    private MatchStatus status; // PENDING, ACCEPTED, REJECTED, ACTIVE, COMPLETED

    @Column(name = "patient_summary", columnDefinition = "TEXT")
    private String patientSummary; // AI-generated summary for therapist

    @Column(name = "match_date")
    private LocalDateTime matchDate;

    @Column(name = "session_count")
    private Integer sessionCount = 0;

    @Column(name = "last_session_date")
    private LocalDateTime lastSessionDate;

    @PrePersist
    protected void onCreate() {
        matchDate = LocalDateTime.now();
    }

    public enum MatchStatus {
        PENDING, ACCEPTED, REJECTED, ACTIVE, COMPLETED
    }
}
