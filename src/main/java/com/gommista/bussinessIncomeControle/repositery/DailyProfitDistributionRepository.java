package com.gommista.bussinessIncomeControle.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gommista.bussinessIncomeControle.entities.DailyProfitDistribution;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyProfitDistributionRepository extends JpaRepository<DailyProfitDistribution, Long> {

    // Custom query to find distributions by date
    List<DailyProfitDistribution> findByDistributionDate(LocalDate distributionDate);

    // Custom query to find distributions within a date range
    List<DailyProfitDistribution> findByDistributionDateBetween(LocalDate startDate, LocalDate endDate);
}

