package com.microservice.module_portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProjectRequest {

    @NotBlank(message = "Project title is required")
    private String title;

    private String description;
    private String techStack;
    private LocalDate startDate;
    private LocalDate endDate;

    // ❌ Supprimé : private String projectUrl;
    // ✅ Remplacé par :
    private String githubUrl;
    private String demoUrl;
    private String images;
}