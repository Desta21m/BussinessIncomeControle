package com.gommista.bussinessIncomeControle.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.gommista.bussinessIncomeControle.dto.newPetroliemDTO;
import com.gommista.bussinessIncomeControle.dto.dailyFormDto;
import com.gommista.bussinessIncomeControle.entities.DailyProfitDistribution;
import com.gommista.bussinessIncomeControle.entities.MonthlyIncomeDistribution;
import com.gommista.bussinessIncomeControle.entities.PetroleumTransaction;
import com.gommista.bussinessIncomeControle.repositery.DailyProfitDistributionRepository;
import com.gommista.bussinessIncomeControle.repositery.MonthlyIncomeDistributionRepository;
import com.gommista.bussinessIncomeControle.repositery.PetroleumTransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Cashari {
    private final PetroleumTransactionRepository petroleumTransactionRepository;
    private final DailyProfitDistributionRepository dailyProfitDistributionRepository;
    private final MonthlyIncomeDistributionRepository monthlyIncomeDistributionRepository;

    public PetroleumTransaction buyPetroleum(newPetroliemDTO Petroliem) {
        // Check if the incoming DTO is null
        if (Petroliem == null) {
            throw new IllegalArgumentException("Petroleum DTO cannot be null");
        }

        // Check if the active petroleum transaction exists
        PetroleumTransaction ActivePT = petroleumTransactionRepository.findByIsActive(true);
        MonthlyIncomeDistribution thisMonth = monthlyIncomeDistributionRepository.findByIsActive(true);

        // Check for null before using ActivePT or thisMonth
        if (thisMonth == null) {
            throw new IllegalStateException("No active monthly income distribution found");
        }

        // Safely modify thisMonth without null issues
        double newOwnerProfit = thisMonth.getOwnerProfit() - Petroliem.getCost();
        thisMonth.setOwnerProfit(newOwnerProfit);
        monthlyIncomeDistributionRepository.save(thisMonth);

        if (ActivePT == null) {
            // Create and save a new PetroleumTransaction if no active one exists
            PetroleumTransaction pt = new PetroleumTransaction();
            pt.setCost(Petroliem.getCost());
            pt.setExpectedIncome(Petroliem.getExpectedIncome());
            pt.setLeftedIncomeFromPrevious(0);
            pt.setRemainingExpectedIncome(0);
            pt.setActive(true);
            pt.setPaied(false);
            pt.setPurchaseDate(LocalDate.now());

            return petroleumTransactionRepository.save(pt);
        } else {
            // If there is an active PetroleumTransaction, update it and create a new one
            ActivePT.setActive(false);
            petroleumTransactionRepository.save(ActivePT);

            PetroleumTransaction pt = new PetroleumTransaction();
            pt.setActive(true);
            pt.setPaied(false);
            pt.setCost(Petroliem.getCost());
            pt.setExpectedIncome(Petroliem.getExpectedIncome());
            pt.setLeftedIncomeFromPrevious(ActivePT.getRemainingExpectedIncome());
            pt.setPurchaseDate(LocalDate.now());

            return petroleumTransactionRepository.save(pt);
        }
    }

    public DailyProfitDistribution calculeteTheDailyIncomeDestribution(dailyFormDto form) {
        // Check if the form is null
        if (form == null) {
            throw new IllegalArgumentException("Form cannot be null");
        }

        // Retrieve the active petroleum transaction
        PetroleumTransaction ActivePT = petroleumTransactionRepository.findByIsActive(true);
        if (ActivePT == null) {
            throw new IllegalStateException("No active petroleum transaction found");
        }

        // Calculate total income and other values
        double total = form.getTheOneWithYou() + form.getTheOneWithMe() + form.getAdditionalCost();
        double petrolCost = ActivePT.isPaied() ? 0 : ActivePT.getCost();
        double foodCost = 60 * form.getFoodNumber();

        System.out.println("form.getTheOneWithYou()  "+form.getTheOneWithYou());
        System.out.println("form.getTheOneWithMe()  "+form.getTheOneWithMe());
        System.out.println("form.getAdditionalCost()  "+form.getAdditionalCost());
        System.out.println("form.getFoodNumber()  "+form.getFoodNumber());
        System.out.println("total  "+total);
        System.out.println("foodCost  "+foodCost);


        // Retrieve the active monthly income distribution
        MonthlyIncomeDistribution thisMonth = monthlyIncomeDistributionRepository.findByIsActive(true);
        if (thisMonth == null) {
            throw new IllegalStateException("No active monthly income distribution found");
        }

        // Update the monthly income distribution
        thisMonth.setOwnerProfit(thisMonth.getOwnerProfit() + petrolCost);
        monthlyIncomeDistributionRepository.save(thisMonth);

        // Calculate owner and worker shares
        double ownerShare = (total - petrolCost) / 2;
        double workerShare = ((total - petrolCost) / 2) - (foodCost + form.getAdditionalCost());
        double amountToSendWorkers = form.getTheOneWithYou() - workerShare;

        System.out.println("ownerShare  "+ownerShare);
        System.out.println("workerShare  "+workerShare);
        System.out.println("amountToSendWorkers  "+amountToSendWorkers);

        // Mark the active petroleum transaction as paid
        ActivePT.setPaied(true);
        ActivePT.setRealIncome(ActivePT.getRealIncome()+total);
        ActivePT.setRemainingExpectedIncome(ActivePT.getRemainingExpectedIncome()+(ActivePT.getExpectedIncome()-total));
        petroleumTransactionRepository.save(ActivePT);

        // Create and save the daily profit distribution
        DailyProfitDistribution DPD = new DailyProfitDistribution();
        DPD.setTotalIncome(total);
        DPD.setAdditionalCost(form.getAdditionalCost());
        DPD.setFoodCost(foodCost);
        DPD.setOwnerShare(ownerShare);
        DPD.setWorkerShare(workerShare);
        DPD.setAmountToSendWorkers(amountToSendWorkers);
        DPD.setDistributionDate(LocalDate.now());

        return dailyProfitDistributionRepository.save(DPD);
    }

    public MonthlyIncomeDistribution pushToTotal(Long dpdId, double sent) {
        // Validate input parameters
        if (dpdId == null) {
            throw new IllegalArgumentException("dpdId cannot be null");
        }
        if (sent < 0) {
            throw new IllegalArgumentException("Sent amount cannot be negative");
        }

        // Retrieve the DailyProfitDistribution by ID
        DailyProfitDistribution DPD = dailyProfitDistributionRepository.findById(dpdId).orElse(null);
        if (DPD == null) {
            throw new IllegalStateException("DailyProfitDistribution with ID " + dpdId + " not found");
        }

        // Calculate net profit and worker savings
        double netProfit = DPD.getOwnerShare() - 134;
        double workerSaving = sent - DPD.getAmountToSendWorkers();

        // Retrieve the active MonthlyIncomeDistribution
        MonthlyIncomeDistribution thisMonth = monthlyIncomeDistributionRepository.findByIsActive(true);
        if (thisMonth == null) {
            throw new IllegalStateException("No active MonthlyIncomeDistribution found");
        }

        // Update the MonthlyIncomeDistribution values
        thisMonth.setRent(thisMonth.getRent() + 134);
        thisMonth.setOwnerProfit(thisMonth.getOwnerProfit() + netProfit);
        thisMonth.setWorkerShare(thisMonth.getWorkerShare() + workerSaving);
        thisMonth.setFoodCost(thisMonth.getFoodCost() + DPD.getFoodCost());
        thisMonth.setTotalIncome(thisMonth.getTotalIncome() + DPD.getTotalIncome());

        // Save and return the updated MonthlyIncomeDistribution
        return monthlyIncomeDistributionRepository.save(thisMonth);
    }


public void createMonth(String month) {
    // Validate input
    if (month == null || month.isBlank()) {
        throw new IllegalArgumentException("Month cannot be null or empty");
    }

    // Retrieve the currently active month
    MonthlyIncomeDistribution thisMonth = monthlyIncomeDistributionRepository.findByIsActive(true);

    if (thisMonth != null) {
        // Deactivate the currently active month
        thisMonth.setActive(false);
        monthlyIncomeDistributionRepository.save(thisMonth);
    }

    // Create and activate the new month
    MonthlyIncomeDistribution newMonth = new MonthlyIncomeDistribution();
    newMonth.setMonth(month);
    newMonth.setActive(true);

    // Save the new month
    monthlyIncomeDistributionRepository.save(newMonth);
}

}
