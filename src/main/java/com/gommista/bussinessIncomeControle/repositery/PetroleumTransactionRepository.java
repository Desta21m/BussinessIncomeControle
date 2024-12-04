package com.gommista.bussinessIncomeControle.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gommista.bussinessIncomeControle.entities.PetroleumTransaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PetroleumTransactionRepository extends JpaRepository<PetroleumTransaction, Long> {

    // Custom query to find active transactions
    PetroleumTransaction findByIsActive(boolean isActive);

    // Custom query to find transactions by purchase date
    List<PetroleumTransaction> findByPurchaseDate(LocalDate purchaseDate);

    // Custom query to find transactions by status
    List<PetroleumTransaction> findByStatus(String status);

    // Custom query to find transactions with remaining expected income greater than a certain amount
    List<PetroleumTransaction> findByRemainingExpectedIncomeGreaterThan(BigDecimal amount);
}

