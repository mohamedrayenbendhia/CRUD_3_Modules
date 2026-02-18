package com.microservice.module_competence.controllers;

import com.microservice.module_competence.dto.*;
import com.microservice.module_competence.services.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/skills")
@RequiredArgsConstructor
public class AdminSkillController {

    private final SkillService skillService;

    // POST /api/admin/skills
    @PostMapping
    public ResponseEntity<SkillResponse> create(
            @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(skillService.createSkill(request));
    }

    // GET /api/admin/skills
    @GetMapping
    public ResponseEntity<List<SkillResponse>> getAll() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // GET /api/admin/skills/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    // PUT /api/admin/skills/{id}
    @PutMapping("/{id}")
    public ResponseEntity<SkillResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillService.updateSkill(id, request));
    }

    // DELETE /api/admin/skills/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
