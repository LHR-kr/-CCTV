package com.hanium.if050.domain.video;

import lombok.Builder;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Setter
public class Video {

    @Transient
    public static final String SEQUENCE_NAME="video_sequence";

    @Id
    private Long id;


    private String originFileName;

    private String filePath;

    private String cctvId;

    @Builder
    public Video(String originFileName, String filePath, String cctvId) {
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.cctvId = cctvId;
    }
}
