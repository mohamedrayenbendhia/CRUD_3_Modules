package com.microservice.module_certification.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "certifications")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String organization;

    private LocalDate date;
    private String certificateUrl;

    @Column(nullable = false)
    private Long userSkillId;  // FK vers UserSkill (module-competence)
}