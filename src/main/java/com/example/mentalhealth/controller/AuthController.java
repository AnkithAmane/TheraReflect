package com.example.mentalhealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mentalhealth.dto.LoginRequest;
import com.example.mentalhealth.entity.Patient;
import com.example.mentalhealth.entity.Therapist;
import com.example.mentalhealth.service.PatientService;
import com.example.mentalhealth.service.TherapistService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final PatientService patientService;
    private final TherapistService therapistService;

    // Home page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Signup page
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("therapist", new Therapist());
        return "signup";
    }

    // Patient signup
    @PostMapping("/signup/patient")
    public String signupPatient(@Valid @ModelAttribute Patient patient,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "signup";
        }

        if (patientService.existsByEmail(patient.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "Email already exists!");
            return "redirect:/signup";
        }

        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("success", "Patient registered successfully! Please login.");
        return "redirect:/login";
    }

    // Therapist signup
    @PostMapping("/signup/therapist")
    public String signupTherapist(@Valid @ModelAttribute Therapist therapist,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "signup";
        }

        if (therapistService.existsByEmail(therapist.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "Email already exists!");
            return "redirect:/signup";
        }

        therapistService.saveTherapist(therapist);
        redirectAttributes.addFlashAttribute("success", "Therapist registered successfully! Please login.");
        return "redirect:/login";
    }

    // Login page
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    // Login processing
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginRequest loginRequest,
            BindingResult result,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "login";
        }

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        String userType = loginRequest.getUserType();

        if ("patient".equals(userType)) {
            if (patientService.authenticatePatient(email, password)) {
                Patient patient = patientService.findByEmail(email).get();
                session.setAttribute("user", patient);
                session.setAttribute("userType", "patient");
                return "redirect:/patient/dashboard";
            }
        } else if ("therapist".equals(userType)) {
            if (therapistService.authenticateTherapist(email, password)) {
                Therapist therapist = therapistService.findByEmail(email).get();
                session.setAttribute("user", therapist);
                session.setAttribute("userType", "therapist");
                return "redirect:/therapist/dashboard";
            }
        }

        redirectAttributes.addFlashAttribute("error", "Invalid email or password!");
        return "redirect:/login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // Patient dashboard
    @GetMapping("/patient/dashboard")
    public String patientDashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null || !"patient".equals(session.getAttribute("userType"))) {
            return "redirect:/login";
        }

        Patient patient = (Patient) session.getAttribute("user");
        model.addAttribute("patient", patient);
        return "patient-dashboard";
    }

    // Therapist dashboard
    @GetMapping("/therapist/dashboard")
    public String therapistDashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null || !"therapist".equals(session.getAttribute("userType"))) {
            return "redirect:/login";
        }

        Therapist therapist = (Therapist) session.getAttribute("user");
        model.addAttribute("therapist", therapist);
        return "therapist-dashboard";
    }
}
