package com.microservice.module_certification.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CertificationResponse {
    private Long id;
    private String title;
    private String organization;
    private LocalDate date;
    private String certificateUrl;
    private Long userSkillId;
}
