package com.microservice.module_competence.services;

import com.microservice.module_competence.dto.*;
import com.microservice.module_competence.entities.*;
import com.microservice.module_competence.enums.Level;
import com.microservice.module_competence.exceptions.*;
import com.microservice.module_competence.repositories.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final SkillService skillService;

    // ── Add skill to user
    @Transactional
    public UserSkillResponse addSkillToUser(UserSkillRequest request) {
        if (userSkillRepository.existsByUserIdAndSkillId(request.getUserId(), request.getSkillId())) {
            throw new DuplicateResourceException("User already has this skill");
        }
        Skill skill = skillService.findById(request.getSkillId());
        UserSkill userSkill = UserSkill.builder()
                .userId(request.getUserId())
                .skill(skill)
                .level(request.getLevel())
                .yearsOfExperience(request.getYearsOfExperience())
                .build();
        return toResponse(userSkillRepository.save(userSkill));
    }

    // ── Get all skills for a user
    public List<UserSkillResponse> getSkillsByUser(Long userId) {
        return userSkillRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ── Get skills by user and level
    public List<UserSkillResponse> getSkillsByUserAndLevel(Long userId, Level level) {
        return userSkillRepository.findByUserIdAndLevel(userId, level)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ── Get single UserSkill by id
    public UserSkillResponse getUserSkillById(Long id) {
        return toResponse(findById(id));
    }

    // ── Update level / experience
    @Transactional
    public UserSkillResponse updateUserSkill(Long id, UserSkillRequest request) {
        UserSkill userSkill = findById(id);
        Skill skill = skillService.findById(request.getSkillId());
        userSkill.setSkill(skill);
        userSkill.setLevel(request.getLevel());
        userSkill.setYearsOfExperience(request.getYearsOfExperience());
        return toResponse(userSkillRepository.save(userSkill));
    }

    // ── Delete
    public void deleteUserSkill(Long id) {
        if (!userSkillRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserSkill not found with id: " + id);
        }
        userSkillRepository.deleteById(id);
    }

    // ── Mapper
    private UserSkillResponse toResponse(UserSkill us) {
        return UserSkillResponse.builder()
                .id(us.getId())
                .userId(us.getUserId())
                .skill(SkillResponse.builder()
                        .id(us.getSkill().getId())
                        .name(us.getSkill().getName())
                        .build())
                .level(us.getLevel())
                .yearsOfExperience(us.getYearsOfExperience())
                .build();
    }

    public UserSkill findById(Long id) {
        return userSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserSkill not found with id: " + id));
    }
}
