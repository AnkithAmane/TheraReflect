package com.example.mentalhealth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Therapist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Column(columnDefinition = "TEXT")
    private String specialty;

    private String availability;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Column(name = "specialties", columnDefinition = "TEXT")
    private String specialties; // JSON array of specialty IDs

    @Column(name = "therapy_approaches", columnDefinition = "TEXT")
    private String therapyApproaches; // JSON array of therapy approaches (CBT, DBT, etc.)
}
