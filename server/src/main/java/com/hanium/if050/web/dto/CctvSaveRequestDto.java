package com.hanium.if050.web.dto;

import com.hanium.if050.domain.cctv.Cctv;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CctvSaveRequestDto {

    private String cctvID;

    private String cctvUrl;

    @Builder
    public CctvSaveRequestDto(String cctvId, String cctvUrl) {
        this.cctvID = cctvId;
        this.cctvUrl = cctvUrl;

    }

    public Cctv toEntity() {
        return Cctv.builder()
                .cctvID(cctvID)
                .cctvUrl(cctvUrl)
                .build();
    }
}
