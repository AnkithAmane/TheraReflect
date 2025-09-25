package com.example.mentalhealth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.entity.TherapistRecommendation;
import com.example.mentalhealth.repository.PatientRepository;
import com.example.mentalhealth.service.TherapistRecommendationService;

@Controller
@RequestMapping("/therapist-recommendations")
public class TherapistRecommendationController {

    @Autowired
    private TherapistRecommendationService recommendationService;

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Generate and display therapist recommendations for a patient
     */
    @GetMapping("/generate/{patientId}")
    public String generateRecommendations(@PathVariable Long patientId, Model model) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (!patientOpt.isPresent()) {
            return "redirect:/patients?error=Patient not found";
        }

        Patient patient = patientOpt.get();

        // Generate recommendations using AI analysis
        List<TherapistRecommendation> recommendations
                = recommendationService.generateRecommendations(patient);

        model.addAttribute("patient", patient);
        model.addAttribute("recommendations", recommendations);
        model.addAttribute("pageTitle", "Therapist Recommendations");

        return "therapist-recommendations";
    }

    /**
     * View existing recommendations for a patient
     */
    @GetMapping("/view/{patientId}")
    public String viewRecommendations(@PathVariable Long patientId, Model model) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (!patientOpt.isPresent()) {
            return "redirect:/patients?error=Patient not found";
        }

        Patient patient = patientOpt.get();
        List<TherapistRecommendation> recommendations
                = recommendationService.getRecommendationsForPatient(patient);

        model.addAttribute("patient", patient);
        model.addAttribute("recommendations", recommendations);
        model.addAttribute("pageTitle", "Your Recommended Therapists");

        return "therapist-recommendations";
    }

    /**
     * Patient selects a therapist to create a match
     */
    @PostMapping("/select")
    public String selectTherapist(@RequestParam Long patientId,
            @RequestParam Long therapistId,
            RedirectAttributes redirectAttributes) {

        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (!patientOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Patient not found");
            return "redirect:/patients";
        }

        // This would normally find the therapist, but for now we'll create a mock match
        Patient patient = patientOpt.get();

        try {
            // Create the patient-therapist match
            // PatientTherapistMatch match = recommendationService.createMatch(patient, therapist);

            redirectAttributes.addFlashAttribute("success",
                    "Therapist selection submitted! You will be contacted soon.");

            return "redirect:/therapist-recommendations/view/" + patientId;

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Error processing your selection. Please try again.");
            return "redirect:/therapist-recommendations/view/" + patientId;
        }
    }

    /**
     * API endpoint to get patient analysis summary for therapists
     */
    @GetMapping("/api/patient-summary/{patientId}")
    @ResponseBody
    public String getPatientSummary(@PathVariable Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (!patientOpt.isPresent()) {
            return "Patient not found";
        }

        return recommendationService.generatePatientSummary(patientOpt.get());
    }

    /**
     * Dashboard for therapists to view their matched patients
     */
    @GetMapping("/therapist-dashboard/{therapistId}")
    public String therapistDashboard(@PathVariable Long therapistId, Model model) {
        // This would show all patients matched to this therapist
        model.addAttribute("pageTitle", "Therapist Dashboard");
        model.addAttribute("therapistId", therapistId);

        return "therapist-dashboard";
    }
}
