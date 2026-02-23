package com.microservice.module_portfolio.controllers;

import com.microservice.module_portfolio.dto.PortfolioResponse;
import com.microservice.module_portfolio.services.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/portfolios")
@RequiredArgsConstructor
public class ClientPortfolioController {

    private final PortfolioService portfolioService;

    // GET /api/client/portfolios/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<PortfolioResponse> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getByUserId(userId));
    }
}