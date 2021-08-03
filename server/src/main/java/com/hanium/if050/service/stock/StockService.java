package com.hanium.if050.service.stock;

import com.hanium.if050.domain.stock.Stock;
import com.hanium.if050.domain.stock.StockRepository;
import com.hanium.if050.web.dto.StockSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    public String stockSave(String id,StockSaveRequestDto requestDto) {
        Optional<Stock> stock = stockRepository.findById(id);

        stock.ifPresent(selectStock -> {
                selectStock.update(requestDto);
            }
        );

        return id;

    }
}
