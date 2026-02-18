package com.microservice.module_portfolio.dto;

import lombok.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PortfolioResponse {
    private Long id;
    private Long userId;
    private String headline;

    // ❌ Supprimé : description, phone, profileImageUrl
    private String linkedinUrl;
    private String githubUrl;
    private String location;
    private List<ProjectResponse> projects;
}