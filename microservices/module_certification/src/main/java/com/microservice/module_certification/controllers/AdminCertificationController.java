package com.microservice.module_certification.controllers;

import com.microservice.module_certification.dto.*;
import com.microservice.module_certification.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/certifications")
@RequiredArgsConstructor
public class AdminCertificationController {

    private final CertificationService certificationService;
    private final TestService testService;

    // ── Certifications
    // GET /api/admin/certifications
    @GetMapping
    public ResponseEntity<List<CertificationResponse>> getAll() {
        return ResponseEntity.ok(certificationService.getAll());
    }

    // DELETE /api/admin/certifications/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable Long id) {
        certificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ── Tests
    // POST /api/admin/certifications/tests
    @PostMapping("/tests")
    public ResponseEntity<TestResponse> createTest(
            @Valid @RequestBody TestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(testService.create(request));
    }

    // GET /api/admin/certifications/tests
    @GetMapping("/tests")
    public ResponseEntity<List<TestResponse>> getAllTests() {
        return ResponseEntity.ok(testService.getAll());
    }

    // GET /api/admin/certifications/tests/{id}
    @GetMapping("/tests/{id}")
    public ResponseEntity<TestResponse> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getById(id));
    }

    // POST /api/admin/certifications/tests/{testId}/questions
    @PostMapping("/tests/{testId}/questions")
    public ResponseEntity<QuestionResponse> addQuestion(
            @PathVariable Long testId,
            @Valid @RequestBody QuestionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(testService.addQuestion(testId, request));
    }

    // DELETE /api/admin/certifications/tests/{id}
    @DeleteMapping("/tests/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        testService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
