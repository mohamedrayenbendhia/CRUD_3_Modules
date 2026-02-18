package com.microservice.module_certification.controllers;

import com.microservice.module_certification.dto.*;
import com.microservice.module_certification.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/freelancer/certifications")
@RequiredArgsConstructor
public class FreelancerCertificationController {

    private final CertificationService certificationService;
    private final TestService testService;
    private final UserTestResultService userTestResultService;

    // ── Certifications
    // POST /api/freelancer/certifications
    @PostMapping
    public ResponseEntity<CertificationResponse> addCertification(
            @Valid @RequestBody CertificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(certificationService.create(request));
    }

    // GET /api/freelancer/certifications/user-skill/{userSkillId}
    @GetMapping("/user-skill/{userSkillId}")
    public ResponseEntity<List<CertificationResponse>> getMyCertifications(
            @PathVariable Long userSkillId) {
        return ResponseEntity.ok(certificationService.getByUserSkillId(userSkillId));
    }

    // PUT /api/freelancer/certifications/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CertificationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CertificationRequest request) {
        return ResponseEntity.ok(certificationService.update(id, request));
    }

    // DELETE /api/freelancer/certifications/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        certificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ── Tests
    // GET /api/freelancer/certifications/tests/skill/{skillId}
    // Voir le test lié à une compétence
    @GetMapping("/tests/skill/{skillId}")
    public ResponseEntity<TestResponse> getTestBySkill(@PathVariable Long skillId) {
        return ResponseEntity.ok(testService.getBySkillId(skillId));
    }

    // ── Passer un test
    // POST /api/freelancer/certifications/tests/submit
    @PostMapping("/tests/submit")
    public ResponseEntity<UserTestResultResponse> submitTest(
            @Valid @RequestBody SubmitTestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userTestResultService.submitTest(request));
    }

    // GET /api/freelancer/certifications/results/{userId}
    @GetMapping("/results/{userId}")
    public ResponseEntity<List<UserTestResultResponse>> getMyResults(
            @PathVariable Long userId) {
        return ResponseEntity.ok(userTestResultService.getByUserId(userId));
    }

    // GET /api/freelancer/certifications/results/skill/{userSkillId}
    @GetMapping("/results/skill/{userSkillId}")
    public ResponseEntity<List<UserTestResultResponse>> getResultsBySkill(
            @PathVariable Long userSkillId) {
        return ResponseEntity.ok(userTestResultService.getByUserSkillId(userSkillId));
    }
}
