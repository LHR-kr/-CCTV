package com.hanium.if050.domain.sum;

import com.hanium.if050.domain.stock.Stock;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Calculate {

    @Transient
    public static final String SEQUENCE_NAME="calculate_sequence";

    @Id
    private Long id;

    private String cctvId;

    private String chocopie;

    private String frenchpie;

    private String margaret;

    private String moncher;

    @Builder
    public Calculate(String cctvId, String chocopie, String frenchpie, String margaret, String moncher) {
        this.cctvId = cctvId;
        this.chocopie = chocopie;
        this.frenchpie = frenchpie;
        this.margaret = margaret;
        this.moncher = moncher;
    }
}
