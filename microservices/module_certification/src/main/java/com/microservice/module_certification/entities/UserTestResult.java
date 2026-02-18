package com.microservice.module_certification.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_test_results")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserTestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(nullable = false)
    private Long userSkillId;  // FK vers UserSkill (module-competence)

    private int score;         // ex: 75 (%)
    private boolean isPassed;
    private LocalDateTime passedAt;

    @OneToMany(mappedBy = "userTestResult", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserAnswer> answers;
}
