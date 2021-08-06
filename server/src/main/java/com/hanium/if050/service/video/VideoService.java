package com.hanium.if050.service.video;

import com.hanium.if050.domain.video.VideoRepository;
import com.hanium.if050.web.dto.VideoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public void saveVideo(VideoSaveRequestDto requestDto) {
        videoRepository.save(requestDto.toEntity());
    }

}
