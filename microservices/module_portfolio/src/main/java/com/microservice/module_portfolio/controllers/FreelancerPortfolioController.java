package com.microservice.module_portfolio.controllers;

import com.microservice.module_portfolio.dto.*;
import com.microservice.module_portfolio.services.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/freelancer/portfolios")
@RequiredArgsConstructor
public class FreelancerPortfolioController {

    private final PortfolioService portfolioService;

    // POST /api/freelancer/portfolios
    @PostMapping
    public ResponseEntity<PortfolioResponse> create(
            @Valid @RequestBody PortfolioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(portfolioService.createPortfolio(request));
    }

    // GET /api/freelancer/portfolios/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<PortfolioResponse> getMyPortfolio(
            @PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getByUserId(userId));
    }

    // PUT /api/freelancer/portfolios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PortfolioResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PortfolioUpdateRequest request) {
        return ResponseEntity.ok(portfolioService.updatePortfolio(id, request));
    }

    // POST /api/freelancer/portfolios/{portfolioId}/projects
    @PostMapping("/{portfolioId}/projects")
    public ResponseEntity<ProjectResponse> addProject(
            @PathVariable Long portfolioId,
            @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(portfolioService.addProject(portfolioId, request));
    }

    // GET /api/freelancer/portfolios/{portfolioId}/projects
    @GetMapping("/{portfolioId}/projects")
    public ResponseEntity<List<ProjectResponse>> getProjects(
            @PathVariable Long portfolioId) {
        return ResponseEntity.ok(portfolioService.getProjects(portfolioId));
    }

    // PUT /api/freelancer/portfolios/projects/{projectId}
    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long projectId,
            @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(portfolioService.updateProject(projectId, request));
    }

    // DELETE /api/freelancer/portfolios/projects/{projectId}
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        portfolioService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}