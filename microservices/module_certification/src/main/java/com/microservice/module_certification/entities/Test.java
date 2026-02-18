package com.microservice.module_certification.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long skillId;

    @Column(nullable = false)
    private int passingScore;  // ← AJOUTÉ ex: 70 (%)

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Question> questions;
}