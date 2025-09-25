package com.example.mentalhealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mentalhealth.entity.Journal;
import com.example.mentalhealth.entity.Patient;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    List<Journal> findByPatientOrderByCreatedDateDesc(Patient patient);

    List<Journal> findByPatient(Patient patient);

    List<Journal> findByPatientAndDetectedIssuesContaining(Patient patient, String issue);
}
