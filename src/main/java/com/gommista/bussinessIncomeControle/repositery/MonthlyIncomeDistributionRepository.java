package com.gommista.bussinessIncomeControle.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gommista.bussinessIncomeControle.entities.MonthlyIncomeDistribution;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MonthlyIncomeDistributionRepository extends JpaRepository<MonthlyIncomeDistribution, Long> {

    // Custom query to find records by month
    List<MonthlyIncomeDistribution> findByMonth(String month);

    // Custom query to find records where the rent exceeds a certain amount
    MonthlyIncomeDistribution findByIsActive(boolean isActive);

    // Custom query to find records by total income range
    List<MonthlyIncomeDistribution> findByTotalIncomeBetween(BigDecimal minIncome, BigDecimal maxIncome);
}

