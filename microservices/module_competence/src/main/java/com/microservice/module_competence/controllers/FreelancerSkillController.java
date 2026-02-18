package com.microservice.module_competence.controllers;

import com.microservice.module_competence.dto.*;
import com.microservice.module_competence.enums.Level;
import com.microservice.module_competence.services.SkillService;
import com.microservice.module_competence.services.UserSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/freelancer/skills")
@RequiredArgsConstructor
public class FreelancerSkillController {

    private final UserSkillService userSkillService;
    private final SkillService skillService;

    // GET /api/freelancer/skills
    // Voir toutes les compétences disponibles
    @GetMapping
    public ResponseEntity<List<SkillResponse>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // POST /api/freelancer/skills/user-skills
    // Ajouter une compétence à son profil
    @PostMapping("/user-skills")
    public ResponseEntity<UserSkillResponse> addSkill(
            @Valid @RequestBody UserSkillRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userSkillService.addSkillToUser(request));
    }

    // GET /api/freelancer/skills/user-skills/{userId}
    // Voir ses propres compétences
    @GetMapping("/user-skills/{userId}")
    public ResponseEntity<List<UserSkillResponse>> getMySkills(
            @PathVariable Long userId) {
        return ResponseEntity.ok(userSkillService.getSkillsByUser(userId));
    }

    // GET /api/freelancer/skills/user-skills/{userId}/level?level=EXPERT
    // Filtrer ses compétences par niveau
    @GetMapping("/user-skills/{userId}/level")
    public ResponseEntity<List<UserSkillResponse>> getMySkillsByLevel(
            @PathVariable Long userId,
            @RequestParam Level level) {
        return ResponseEntity.ok(userSkillService.getSkillsByUserAndLevel(userId, level));
    }

    // PUT /api/freelancer/skills/user-skills/{id}
    // Modifier une compétence
    @PutMapping("/user-skills/{id}")
    public ResponseEntity<UserSkillResponse> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody UserSkillRequest request) {
        return ResponseEntity.ok(userSkillService.updateUserSkill(id, request));
    }

    // DELETE /api/freelancer/skills/user-skills/{id}
    // Supprimer une compétence
    @DeleteMapping("/user-skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        userSkillService.deleteUserSkill(id);
        return ResponseEntity.noContent().build();
    }
}
