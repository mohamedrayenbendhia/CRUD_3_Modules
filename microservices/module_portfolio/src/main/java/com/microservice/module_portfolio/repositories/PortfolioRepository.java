package com.microservice.module_portfolio.repositories;

import com.microservice.module_portfolio.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
