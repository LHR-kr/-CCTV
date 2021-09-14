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

    private String chocopie;

    private String frenchpie;

    private String margaret;

    private String moncher;

    @Builder
    public Stock(String cctvID, String chocopie,String frenchpie, String margaret, String moncher) {
        this.cctvID = cctvID;
        this.chocopie = chocopie;
        this.frenchpie = frenchpie;
        this.margaret = margaret;
        this.moncher = moncher;

    }

    public void update(StockSaveRequestDto requestDto) {
        this.chocopie = requestDto.getChocopie();
        this.frenchpie = requestDto.getFrenchpie();
        this.margaret = requestDto.getMargaret();
        this.moncher = requestDto.getMoncher();
    }

}
