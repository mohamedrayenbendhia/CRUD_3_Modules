package com.microservice.module_certification.repositories;

import com.microservice.module_certification.entities.UserTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserTestResultRepository extends JpaRepository<UserTestResult, Long> {
    List<UserTestResult> findByUserId(Long userId);
    List<UserTestResult> findByUserSkillId(Long userSkillId);
    boolean existsByUserIdAndTestId(Long userId, Long testId);
}
