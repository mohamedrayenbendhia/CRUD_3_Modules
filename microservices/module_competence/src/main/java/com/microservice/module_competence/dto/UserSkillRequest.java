package com.microservice.module_competence.dto;

import com.microservice.module_competence.enums.Level;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserSkillRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "skillId is required")
    private Long skillId;

    @NotNull(message = "level is required")
    private Level level;

    @Min(value = 0, message = "Years of experience cannot be negative")
    @Max(value = 50, message = "Years of experience seems too high")
    private int yearsOfExperience;
}
