package com.hanium.if050.service.stock;

import com.hanium.if050.domain.stock.Stock;
import com.hanium.if050.domain.stock.StockRepository;
import com.hanium.if050.web.dto.StockSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public String stockSave(String id,StockSaveRequestDto requestDto) {
        Optional<Stock> stock = stockRepository.findById(id);


        if (stock.isPresent()) {
            stock.get().update(requestDto);
            stockRepository.save(stock.get());
            return stock.get().getCctvID();
        }else {
            Stock newStock = requestDto.toEntity(id);
            stockRepository.save(newStock);
            return newStock.getCctvID();
        }

    }

    @Transactional
    public Stock find(String id) {
        Optional<Stock> stock = stockRepository.findById(id);


        return stock.orElse(null);

    }
}
