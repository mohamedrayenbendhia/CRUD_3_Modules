package com.microservice.module_competence.repositories;

import com.microservice.module_competence.entities.UserSkill;
import com.microservice.module_competence.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    List<UserSkill> findByUserId(Long userId);
    List<UserSkill> findByUserIdAndLevel(Long userId, Level level);
    Optional<UserSkill> findByUserIdAndSkillId(Long userId, Long skillId);
    boolean existsByUserIdAndSkillId(Long userId, Long skillId);
}
