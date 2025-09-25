package com.example.mentalhealth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mentalhealth.entity.Therapist;
import com.example.mentalhealth.repository.TherapistRepository;
import com.example.mentalhealth.util.PasswordUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TherapistService {

    private final TherapistRepository therapistRepository;

    public Therapist saveTherapist(Therapist therapist) {
        // Hash the password before saving
        therapist.setPassword(PasswordUtil.hashPassword(therapist.getPassword()));
        return therapistRepository.save(therapist);
    }

    public List<Therapist> getAllTherapists() {
        return therapistRepository.findAll();
    }

    public Optional<Therapist> findByEmail(String email) {
        return therapistRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return therapistRepository.existsByEmail(email);
    }

    public boolean authenticateTherapist(String email, String password) {
        Optional<Therapist> therapist = findByEmail(email);
        return therapist.isPresent() && PasswordUtil.checkPassword(password, therapist.get().getPassword());
    }
}
