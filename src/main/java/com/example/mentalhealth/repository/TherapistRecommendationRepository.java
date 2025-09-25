package com.example.mentalhealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.entity.TherapistRecommendation;

@Repository
public interface TherapistRecommendationRepository extends JpaRepository<TherapistRecommendation, Long> {

    List<TherapistRecommendation> findByPatientOrderByRecommendationScoreDesc(Patient patient);

    @Query("SELECT tr FROM TherapistRecommendation tr WHERE tr.patient.id = ?1 ORDER BY tr.recommendationScore DESC")
    List<TherapistRecommendation> findTopRecommendationsForPatient(Long patientId);
}
