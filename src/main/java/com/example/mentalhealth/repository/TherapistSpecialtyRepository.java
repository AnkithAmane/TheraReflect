package com.example.mentalhealth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mentalhealth.entity.TherapistSpecialty;

@Repository
public interface TherapistSpecialtyRepository extends JpaRepository<TherapistSpecialty, Long> {

    Optional<TherapistSpecialty> findByName(String name);

    List<TherapistSpecialty> findByNameContainingIgnoreCase(String keyword);
}
