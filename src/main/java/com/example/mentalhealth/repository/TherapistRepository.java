package com.example.mentalhealth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mentalhealth.entity.Therapist;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {

    // Find therapists by availability status
    List<Therapist> findByIsAvailable(boolean isAvailable);

    // Find therapist by email
    Optional<Therapist> findByEmail(String email);

    // Find therapists by specialty
    List<Therapist> findBySpecialtyContainingIgnoreCase(String specialty);

    // Check if therapist exists by email
    boolean existsByEmail(String email);
}
