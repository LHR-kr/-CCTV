package com.hanium.if050.web.controller;

import com.hanium.if050.domain.sum.Calculate;
import com.hanium.if050.service.CalculateService;
import com.hanium.if050.web.dto.SumSaveRequestDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CalculateController {

    private final CalculateService calculateService;

    @PostMapping("/calculate")
    public saveResponse saveSum(@RequestBody  SumSaveRequestDto requestDto) {
        return new saveResponse(calculateService.saveSum(requestDto));
    }

    @GetMapping("/calculate/{cctvId}")
    public Calculate getSum(@PathVariable String cctvId) {
        Calculate calculate = calculateService.getSum(cctvId);
        return calculate;

    }

    @Data
    public class saveResponse{
        boolean status;

        saveResponse(boolean status) {
            this.status = status;
        }
    }
}
