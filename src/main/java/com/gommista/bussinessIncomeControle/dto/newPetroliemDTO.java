package com.gommista.bussinessIncomeControle.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class newPetroliemDTO {
    private double cost;
    private double expectedIncome;
}
