package com.microservice.module_certification.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserTestResultResponse {
    private Long id;
    private Long userId;
    private Long testId;
    private Long userSkillId;
    private int score;
    private int passingScore;   // ← AJOUTÉ pour afficher le seuil
    private boolean isPassed;
    private LocalDateTime passedAt;
    private List<UserAnswerResponse> answers;
}