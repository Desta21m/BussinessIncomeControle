package com.gommista.bussinessIncomeControle.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "DailyProfitDistribution")
public class DailyProfitDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalIncome;
    private double additionalCost;
    private double ownerShare;
    private double workerShare;
    private double foodCost;
    private double amountToSendWorkers;
    private LocalDate distributionDate;

}

