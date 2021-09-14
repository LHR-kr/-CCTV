package com.hanium.if050.web.controller;

import com.hanium.if050.domain.stock.Stock;
import com.hanium.if050.service.stock.StockService;
import com.hanium.if050.web.dto.StockSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockService stockService;

    @PostMapping("/stock/{id}")
    public String stockSave(@PathVariable("id") String id, @RequestBody StockSaveRequestDto requestDto) {
        return stockService.stockSave(id, requestDto);
    }

    @GetMapping("/stock/{id}")
    public Stock stockFind(@PathVariable String id) {
        return stockService.find(id);
    }
}
