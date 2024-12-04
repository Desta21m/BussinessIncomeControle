package com.gommista.bussinessIncomeControle.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PetroleumTransaction")
public class PetroleumTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double leftedIncomeFromPrevious;
    private double expectedIncome;
    private double cost;
    private double realIncome;
    private double remainingExpectedIncome;
    private LocalDate purchaseDate;

    private boolean isActive;
    private boolean isPaied;

    @Column(length = 50)
    private String status;

}

