package com.hanium.if050.service.video;

import com.hanium.if050.domain.video.Video;
import com.hanium.if050.domain.video.VideoRepository;
import com.hanium.if050.web.dto.VideoListDto;
import com.hanium.if050.web.dto.VideoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Transactional
    public Long saveVideo(VideoSaveRequestDto requestDto) {

        Video video = requestDto.toEntity();
        videoRepository.save(video);
        return video.getId();
    }


}
