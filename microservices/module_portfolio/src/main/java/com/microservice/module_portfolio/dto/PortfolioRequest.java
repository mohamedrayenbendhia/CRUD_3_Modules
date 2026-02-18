package com.microservice.module_portfolio.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PortfolioRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotBlank(message = "Headline is required")
    private String headline;

    // ❌ Supprimé : description, phone, profileImageUrl
    private String linkedinUrl;
    private String githubUrl;
    private String location;
    private List<ProjectRequest> projects;
}