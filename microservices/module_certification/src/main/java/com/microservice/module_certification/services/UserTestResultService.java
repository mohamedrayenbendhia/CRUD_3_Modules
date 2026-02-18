package com.microservice.module_certification.services;

import com.microservice.module_certification.dto.*;
import com.microservice.module_certification.entities.*;
import com.microservice.module_certification.exceptions.*;
import com.microservice.module_certification.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTestResultService {

    private final UserTestResultRepository userTestResultRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final QuestionRepository questionRepository;
    private final CertificationRepository certificationRepository;
    private final TestService testService;

    // ── Soumettre un test (flux complet)
    @Transactional
    public UserTestResultResponse submitTest(SubmitTestRequest request) {

        // 1. Vérifier si le freelancer a déjà passé ce test
        if (userTestResultRepository.existsByUserIdAndTestId(
                request.getUserId(), request.getTestId())) {
            throw new DuplicateResourceException(
                    "User already passed this test");
        }

        // 2. Récupérer le test
        Test test = testService.findById(request.getTestId());

        // 3. Créer le résultat
        UserTestResult result = UserTestResult.builder()
                .userId(request.getUserId())
                .test(test)
                .userSkillId(request.getUserSkillId())
                .passedAt(LocalDateTime.now())
                .build();
        UserTestResult saved = userTestResultRepository.save(result);

        // 4. Traiter les réponses
        List<UserAnswer> answers = request.getAnswers().stream().map(a -> {
            Question question = questionRepository.findById(a.getQuestionId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Question not found with id: " + a.getQuestionId()));
            boolean correct = question.getCorrectAnswer()
                    .equalsIgnoreCase(a.getAnswer().trim());
            return UserAnswer.builder()
                    .userId(request.getUserId())
                    .question(question)
                    .userTestResult(saved)
                    .answer(a.getAnswer())
                    .isCorrect(correct)
                    .build();
        }).toList();

        userAnswerRepository.saveAll(answers);

        // 5. Calculer le score
        long correctCount = answers.stream()
                .filter(UserAnswer::isCorrect).count();
        int score = answers.isEmpty() ? 0 :
                (int) ((correctCount * 100) / answers.size());

        // 6. Vérifier si passé selon le passingScore du test
        boolean isPassed = score >= test.getPassingScore();

        // 7. Mettre à jour le résultat
        saved.setScore(score);
        saved.setPassed(isPassed);
        saved.setAnswers(answers);
        userTestResultRepository.save(saved);

        // 8. ✅ Générer automatiquement la Certification si isPassed
        if (isPassed) {
            Certification certification = Certification.builder()
                    .title("Certified: " + test.getTitle())
                    .organization("Smart Freelance Platform")
                    .date(LocalDate.now())
                    .certificateUrl(generateCertificateUrl(
                            request.getUserId(), test.getId()))
                    .userSkillId(request.getUserSkillId())
                    .build();
            certificationRepository.save(certification);
        }

        return toResponse(saved, answers, test.getPassingScore());
    }

    // ── Voir résultats par userId
    public List<UserTestResultResponse> getByUserId(Long userId) {
        return userTestResultRepository.findByUserId(userId)
                .stream().map(r -> toResponse(r,
                        userAnswerRepository.findByUserTestResultId(r.getId()),
                        r.getTest().getPassingScore()))
                .toList();
    }

    // ── Voir résultats par userSkillId
    public List<UserTestResultResponse> getByUserSkillId(Long userSkillId) {
        return userTestResultRepository.findByUserSkillId(userSkillId)
                .stream().map(r -> toResponse(r,
                        userAnswerRepository.findByUserTestResultId(r.getId()),
                        r.getTest().getPassingScore()))
                .toList();
    }

    // ── Générer URL du certificat
    private String generateCertificateUrl(Long userId, Long testId) {
        return "https://platform.com/certificates/"
                + userId + "/" + testId;
    }

    // ── Mapper
    private UserTestResultResponse toResponse(UserTestResult r,
                                              List<UserAnswer> answers, int passingScore) {
        return UserTestResultResponse.builder()
                .id(r.getId())
                .userId(r.getUserId())
                .testId(r.getTest().getId())
                .userSkillId(r.getUserSkillId())
                .score(r.getScore())
                .passingScore(passingScore)
                .isPassed(r.isPassed())
                .passedAt(r.getPassedAt())
                .answers(answers.stream().map(a -> UserAnswerResponse.builder()
                        .id(a.getId())
                        .questionId(a.getQuestion().getId())
                        .answer(a.getAnswer())
                        .isCorrect(a.isCorrect())
                        .build()).toList())
                .build();
    }
}