package com.hanium.if050.service;

import com.hanium.if050.domain.sum.Calculate;
import com.hanium.if050.domain.sum.CalculateRepository;
import com.hanium.if050.web.dto.SumSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CalculateService {

    private final CalculateRepository calculateRepository;

    public boolean saveSum(SumSaveRequestDto requestDto) {
        Calculate calculate = Calculate.builder()
                .chocopie(requestDto.getChocopie())
                .cctvId(requestDto.getCctvId())
                .frenchpie(requestDto.getFrenchpie())
                .margaret(requestDto.getMargaret())
                .moncher(requestDto.getMoncher()).build();

        calculateRepository.save(calculate);

        return true;
    }

    public Calculate getSum(String cctvId) {
        Calculate sum = calculateRepository.findFirstByCctvIdOrderById(cctvId);

        calculateRepository.delete(sum);

        return sum;


    }
}
