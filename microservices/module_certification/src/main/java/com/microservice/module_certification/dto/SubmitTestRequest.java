package com.microservice.module_certification.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SubmitTestRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "userSkillId is required")
    private Long userSkillId;

    @NotNull(message = "answers is required")
    private List<String> answers;
}