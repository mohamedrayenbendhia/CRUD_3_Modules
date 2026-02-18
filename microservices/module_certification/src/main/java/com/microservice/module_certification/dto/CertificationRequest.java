package com.microservice.module_certification.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CertificationRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Organization is required")
    private String organization;

    private LocalDate date;
    private String certificateUrl;

    @NotNull(message = "userSkillId is required")
    private Long userSkillId;
}
