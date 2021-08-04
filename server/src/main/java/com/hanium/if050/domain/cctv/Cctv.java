package com.hanium.if050.domain.cctv;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@NoArgsConstructor
@Document(collection = "cctv")
public class Cctv {

    @Id
    private String cctvID;

    private String cctvUrl;



    @Builder
    public Cctv(String cctvID, String cctvUrl) {
        this.cctvID = cctvID;
        this.cctvUrl = cctvUrl;
    }


}
