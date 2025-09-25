package com.example.mentalhealth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.entity.PatientTherapistMatch;
import com.example.mentalhealth.entity.Therapist;

@Repository
public interface PatientTherapistMatchRepository extends JpaRepository<PatientTherapistMatch, Long> {

    List<PatientTherapistMatch> findByPatient(Patient patient);

    List<PatientTherapistMatch> findByTherapist(Therapist therapist);

    Optional<PatientTherapistMatch> findByPatientAndTherapist(Patient patient, Therapist therapist);

    List<PatientTherapistMatch> findByStatus(PatientTherapistMatch.MatchStatus status);
}
