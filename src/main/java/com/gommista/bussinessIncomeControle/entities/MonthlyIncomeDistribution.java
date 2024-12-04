package com.gommista.bussinessIncomeControle.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "MonthlyIncomeDistribution")
public class MonthlyIncomeDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rent;
    private double ownerProfit;
    private double workerShare;
    private double foodCost;
    private String month; // e.g., "2024-11"
    private double totalIncome;
    private boolean isActive;
}
