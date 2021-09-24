package com.hanium.if050.web.controller;

import com.hanium.if050.service.video.VideoService;
import com.hanium.if050.web.dto.VideoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/video/{id}/save")
    public Long saveVideo(@PathVariable String id, @RequestParam("file") MultipartFile file) {

        try{
            String originFileName = file.getOriginalFilename();
            String savePath = System.getProperty("user.home") + "/files";

            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    return -1L;
                }
            }


            String filePath=savePath+"\\"+originFileName;
            file.transferTo(new File(filePath));

            VideoSaveRequestDto requestDto = new VideoSaveRequestDto();
            requestDto.setCctvId(id);
            requestDto.setFilePath(filePath);
            requestDto.setOriginFileName(originFileName);

            return videoService.saveVideo(requestDto);



        }catch(Exception e){
            return -1L;
        }


    }

}
