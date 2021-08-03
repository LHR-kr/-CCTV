package com.hanium.if050.domain.stock;

import com.hanium.if050.web.dto.StockSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection="stock")
public class Stock {

    @Id
    private String cctvID;

    private Long Chocopie;

    private Long Frenchpie;

    private Long Margaret;

    private Long Moncher;

    @Builder
    public Stock(String cctvID, Long Chocopie, Long Frenchpie, Long Margaret, Long Moncher) {
        this.cctvID = cctvID;
        this.Chocopie = Chocopie;
        this.Frenchpie = Frenchpie;
        this.Margaret = Margaret;
        this.Moncher = Moncher;

    }

    public void update(StockSaveRequestDto requestDto) {
        this.Chocopie = requestDto.getChocopie();
        this.Frenchpie = requestDto.getFrenchpie();
        this.Margaret = requestDto.getMargaret();
        this.Moncher = requestDto.getMoncher();
    }

}
