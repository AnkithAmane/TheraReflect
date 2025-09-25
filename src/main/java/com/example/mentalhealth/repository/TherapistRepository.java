package com.example.mentalhealth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mentalhealth.entity.Therapist;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {

    Optional<Therapist> findByEmail(String email);

    boolean existsByEmail(String email);
}
