package com.microservice.module_certification.services;

import com.microservice.module_certification.dto.*;
import com.microservice.module_certification.entities.*;
import com.microservice.module_certification.exceptions.*;
import com.microservice.module_certification.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;

    // ── Ajouter certification
    @Transactional
    public CertificationResponse create(CertificationRequest request) {
        Certification cert = Certification.builder()
                .title(request.getTitle())
                .organization(request.getOrganization())
                .date(request.getDate())
                .certificateUrl(request.getCertificateUrl())
                .userSkillId(request.getUserSkillId())
                .build();
        return toResponse(certificationRepository.save(cert));
    }

    // ── Voir certifications par userSkillId
    public List<CertificationResponse> getByUserSkillId(Long userSkillId) {
        return certificationRepository.findByUserSkillId(userSkillId)
                .stream().map(this::toResponse).toList();
    }

    // ── Voir toutes les certifications
    public List<CertificationResponse> getAll() {
        return certificationRepository.findAll()
                .stream().map(this::toResponse).toList();
    }

    // ── Modifier certification
    @Transactional
    public CertificationResponse update(Long id, CertificationRequest request) {
        Certification cert = certificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Certification not found with id: " + id));
        cert.setTitle(request.getTitle());
        cert.setOrganization(request.getOrganization());
        cert.setDate(request.getDate());
        cert.setCertificateUrl(request.getCertificateUrl());
        return toResponse(certificationRepository.save(cert));
    }

    // ── Supprimer certification
    @Transactional
    public void delete(Long id) {
        if (!certificationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Certification not found with id: " + id);
        }
        certificationRepository.deleteById(id);
    }

    private CertificationResponse toResponse(Certification c) {
        return CertificationResponse.builder()
                .id(c.getId())
                .title(c.getTitle())
                .organization(c.getOrganization())
                .date(c.getDate())
                .certificateUrl(c.getCertificateUrl())
                .userSkillId(c.getUserSkillId())
                .build();
    }
}
