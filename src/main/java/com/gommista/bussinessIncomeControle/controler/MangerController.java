package com.gommista.bussinessIncomeControle.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gommista.bussinessIncomeControle.entities.DailyProfitDistribution;
import com.gommista.bussinessIncomeControle.entities.MonthlyIncomeDistribution;
import com.gommista.bussinessIncomeControle.entities.PetroleumTransaction;
import com.gommista.bussinessIncomeControle.service.CashariService;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class MangerController {

    private final CashariService cashariService;

    // Get all DailyProfitDistribution entities DONE
    @GetMapping("/daily-profit-distributions")
    public ResponseEntity<List<DailyProfitDistribution>> getAllDailyProfitDistributions() {
        List<DailyProfitDistribution> distributions = cashariService.getAllDailyProfitDistributions();
        return ResponseEntity.ok(distributions);
    }

    // Delete a DailyProfitDistribution by ID DONE
    @DeleteMapping("/daily-profit-distributions/{id}")
    public ResponseEntity<Void> deleteDailyProfitDistributionById(@PathVariable Long id) {
        cashariService.deleteDailyProfitDistributionById(id);
        return ResponseEntity.noContent().build();
    }

    // Get all MonthlyIncomeDistribution entities D
    @GetMapping("/monthly-income-distributions")
    public ResponseEntity<List<MonthlyIncomeDistribution>> getAllMonthlyIncomeDistributions() {
        List<MonthlyIncomeDistribution> distributions = cashariService.getAllMonthlyIncomeDistributions();
        return ResponseEntity.ok(distributions);
    }

    // Get the active MonthlyIncomeDistribution entity   DONE
    @GetMapping("/monthly-income-distributions/active")
    public ResponseEntity<MonthlyIncomeDistribution> getActiveMonthlyIncomeDistribution() {
        MonthlyIncomeDistribution activeDistribution = cashariService.getActiveMonthlyIncomeDistributions();
        return ResponseEntity.ok(activeDistribution);
    }

    // Delete a MonthlyIncomeDistribution by ID DONE
    @DeleteMapping("/monthly-income-distributions/{id}")
    public ResponseEntity<Void> deleteMonthlyIncomeDistributionById(@PathVariable Long id) {
        cashariService.deleteMonthlyIncomeDistributionById(id);
        return ResponseEntity.noContent().build();
    }

    // Get all PetroleumTransaction entities
    @GetMapping("/petroleum-transactions")
    public ResponseEntity<List<PetroleumTransaction>> getAllPetroleumTransactions() {
        List<PetroleumTransaction> transactions = cashariService.getAllPetroleumTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Get the active PetroleumTransaction entity DONE
    @GetMapping("/petroleum-transactions/active")
    public ResponseEntity<PetroleumTransaction> getActivePetroleumTransaction() {
        PetroleumTransaction activeTransaction = cashariService.getActivePetroleumTransactions();
        return ResponseEntity.ok(activeTransaction);
    }

    // Delete a PetroleumTransaction by ID
    @DeleteMapping("/petroleum-transactions/{id}")
    public ResponseEntity<Void> deletePetroleumTransactionById(@PathVariable Long id) {
        cashariService.deletePetroleumTransactionById(id);
        return ResponseEntity.noContent().build();
    }
}

