package com.hanium.if050.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class VideoListDto {

    private Long id;

    private File sumnail;

    private String localDate;
}
