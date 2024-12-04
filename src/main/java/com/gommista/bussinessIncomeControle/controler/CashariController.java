package com.gommista.bussinessIncomeControle.controler;

import com.gommista.bussinessIncomeControle.dto.dailyFormDto;
import com.gommista.bussinessIncomeControle.dto.newPetroliemDTO;
import com.gommista.bussinessIncomeControle.entities.DailyProfitDistribution;
import com.gommista.bussinessIncomeControle.entities.MonthlyIncomeDistribution;
import com.gommista.bussinessIncomeControle.entities.PetroleumTransaction;
import com.gommista.bussinessIncomeControle.service.Cashari;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cashari")
@RequiredArgsConstructor
public class CashariController {

    private final Cashari cashariService;

    //DONE
    @PostMapping("/buy-petroleum")
    public ResponseEntity<PetroleumTransaction> buyPetroleum(@RequestBody newPetroliemDTO petroleumDTO) {
        PetroleumTransaction transaction = cashariService.buyPetroleum(petroleumDTO);
        return ResponseEntity.ok(transaction);
    }

    //DONE
    @PostMapping("/calculate-daily-distribution")
    public ResponseEntity<DailyProfitDistribution> calculateDailyDistribution(@RequestBody dailyFormDto dailyFormDto) {
        DailyProfitDistribution distribution = cashariService.calculeteTheDailyIncomeDestribution(dailyFormDto);
        return ResponseEntity.ok(distribution);
    }

    //DONE
    @PutMapping("/push-to-total/{dpdId}")
    public ResponseEntity<MonthlyIncomeDistribution> pushToTotal(@PathVariable Long dpdId, @RequestParam double sent) {
        MonthlyIncomeDistribution updatedMonth = cashariService.pushToTotal(dpdId, sent);
        return ResponseEntity.ok(updatedMonth);
    }

    //DONE
    @PostMapping("/create-month")
    public ResponseEntity<String> createMonth(@RequestParam String month) {
        cashariService.createMonth(month);
        return ResponseEntity.ok("New month created successfully");
    }
}

