package com.hanium.if050.domain.video;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Document(collection="video")
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
