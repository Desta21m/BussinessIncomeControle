package com.gommista.bussinessIncomeControle.service;

import org.springframework.stereotype.Service;

import com.gommista.bussinessIncomeControle.entities.DailyProfitDistribution;
import com.gommista.bussinessIncomeControle.entities.MonthlyIncomeDistribution;
import com.gommista.bussinessIncomeControle.entities.PetroleumTransaction;
import com.gommista.bussinessIncomeControle.repositery.DailyProfitDistributionRepository;
import com.gommista.bussinessIncomeControle.repositery.MonthlyIncomeDistributionRepository;
import com.gommista.bussinessIncomeControle.repositery.PetroleumTransactionRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashariService {

    private final PetroleumTransactionRepository petroleumTransactionRepository;
    private final DailyProfitDistributionRepository dailyProfitDistributionRepository;
    private final MonthlyIncomeDistributionRepository monthlyIncomeDistributionRepository;


    // Retrieve all DailyProfitDistribution entities
    public List<DailyProfitDistribution> getAllDailyProfitDistributions() {
        return dailyProfitDistributionRepository.findAll();
    }

    // Delete a single DailyProfitDistribution by ID
    public void deleteDailyProfitDistributionById(Long id) {
        if (dailyProfitDistributionRepository.existsById(id)) {
            dailyProfitDistributionRepository.deleteById(id);
        } else {
            throw new RuntimeException("DailyProfitDistribution with ID " + id + " not found");
        }
    }

    // Retrieve all MonthlyIncomeDistribution entities
    public List<MonthlyIncomeDistribution> getAllMonthlyIncomeDistributions() {
        return monthlyIncomeDistributionRepository.findAll();
    }

    // Retrieve only active MonthlyIncomeDistribution entities
    public MonthlyIncomeDistribution getActiveMonthlyIncomeDistributions() {
        return monthlyIncomeDistributionRepository.findByIsActive(true);
    }

    // Delete a single MonthlyIncomeDistribution by ID
    public void deleteMonthlyIncomeDistributionById(Long id) {
        if (monthlyIncomeDistributionRepository.existsById(id)) {
            monthlyIncomeDistributionRepository.deleteById(id);
        } else {
            throw new RuntimeException("MonthlyIncomeDistribution with ID " + id + " not found");
        }
    }

    // Retrieve all PetroleumTransaction entities
    public List<PetroleumTransaction> getAllPetroleumTransactions() {
        return petroleumTransactionRepository.findAll();
    }

    // Retrieve only active PetroleumTransaction entities
    public PetroleumTransaction getActivePetroleumTransactions() {
        return petroleumTransactionRepository.findByIsActive(true);
    }

    // Delete a single PetroleumTransaction by ID
    public void deletePetroleumTransactionById(Long id) {
        if (petroleumTransactionRepository.existsById(id)) {
            petroleumTransactionRepository.deleteById(id);
        } else {
            throw new RuntimeException("PetroleumTransaction with ID " + id + " not found");
        }
    }
}
