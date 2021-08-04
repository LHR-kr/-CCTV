package com.hanium.if050.web.dto;

import com.hanium.if050.domain.video.Video;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class VideoSaveRequestDto {


    private String originFileName;

    private String filePath;

    private String cctvId;

    public Video toEntity(){
        return Video.builder()
                .originFileName(originFileName)
                .filePath(filePath)
                .cctvId(cctvId).build();
    }

}
