package com.gommista.bussinessIncomeControle.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class dailyFormDto {
     private double theOneWithYou;
     private double theOneWithMe;
     private double additionalCost;
     private int foodNumber;
}
