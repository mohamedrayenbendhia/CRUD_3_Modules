package com.microservice.module_portfolio.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private String headline;
    private String linkedinUrl;
    private String githubUrl;
    private String location;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Project> projects= new ArrayList<>();;
}