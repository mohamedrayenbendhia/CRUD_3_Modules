package com.microservice.module_competence.entities;

import com.microservice.module_competence.enums.Level;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "user_skills")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Column(nullable = false)
    private int yearsOfExperience;

    // ✅ Plus de List<Certification> ici — géré par module-certification
}
