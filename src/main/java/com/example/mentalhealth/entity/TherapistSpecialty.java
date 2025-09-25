package com.example.mentalhealth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "therapist_specialties")
public class TherapistSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name; // e.g., "Anxiety", "Depression", "PTSD", "Addiction"

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "keywords", columnDefinition = "TEXT")
    private String keywords; // JSON array of keywords that AI looks for
}
