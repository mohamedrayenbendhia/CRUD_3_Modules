package com.microservice.module_certification.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserAnswerRequest {

    @NotNull(message = "questionId is required")
    private Long questionId;

    @NotBlank(message = "answer is required")
    private String answer;
}
