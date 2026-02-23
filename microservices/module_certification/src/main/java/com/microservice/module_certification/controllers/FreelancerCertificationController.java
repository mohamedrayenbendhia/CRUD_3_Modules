package com.microservice.module_certification.controllers;

import com.microservice.module_certification.dto.*;
import com.microservice.module_certification.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/freelancer")
@RequiredArgsConstructor
public class FreelancerCertificationController {

    private final TestService testService;
    private final UserTestResultService userTestResultService;

    // GET /api/freelancer/tests/skill/{skillId}
    // Voir le test d'un skill (sans correctAnswer)
    @GetMapping("/tests/skill/{skillId}")
    public ResponseEntity<TestPublicResponse> getTestBySkill(
            @PathVariable Long skillId) {
        return ResponseEntity.ok(testService.getBySkillIdPublic(skillId));
    }

    // GET /api/freelancer/tests/results/{userId}
    // Voir ses tests déjà passés
    @GetMapping("/tests/results/{userId}")
    public ResponseEntity<List<UserTestResultResponse>> getMyResults(
            @PathVariable Long userId) {
        return ResponseEntity.ok(userTestResultService.getByUserId(userId));
    }

    // POST /api/freelancer/tests/submit
    // Passer un test
    @PostMapping("/tests/submit")
    public ResponseEntity<UserTestResultResponse> submitTest(
            @Valid @RequestBody SubmitTestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userTestResultService.submitTest(request));
    }

    // GET /api/freelancer/certifications/{userId}
    // Voir ses certifications
    @GetMapping("/certifications/{userId}")
    public ResponseEntity<List<CertificationResponse>> getMyCertifications(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                userTestResultService.getCertificationsByUserId(userId));
    }
}