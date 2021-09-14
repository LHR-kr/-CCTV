package com.hanium.if050.web.dto;

import com.hanium.if050.domain.cctv.Cctv;
import com.hanium.if050.domain.stock.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockSaveRequestDto {

    private String chocopie;

    private String frenchpie;

    private String margaret;

    private String moncher;

    @Builder
    public StockSaveRequestDto(String chocopie, String frenchpie,String marget,String moncher) {
        this.chocopie = chocopie;
        this.frenchpie = frenchpie;
        this.margaret = marget;
        this.moncher = moncher;

    }

    public Stock toEntity(String id) {
        return Stock.builder()
                .cctvID(id)
                .chocopie(chocopie)
                .frenchpie(frenchpie)
                .margaret(margaret)
                .moncher(moncher)
                .build();
    }

}
