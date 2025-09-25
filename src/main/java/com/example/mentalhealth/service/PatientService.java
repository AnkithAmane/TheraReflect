package com.example.mentalhealth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.repository.PatientRepository;
import com.example.mentalhealth.util.PasswordUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        // Hash the password before saving
        patient.setPassword(PasswordUtil.hashPassword(patient.getPassword()));
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    public boolean authenticatePatient(String email, String password) {
        Optional<Patient> patient = findByEmail(email);
        return patient.isPresent() && PasswordUtil.checkPassword(password, patient.get().getPassword());
    }
}
