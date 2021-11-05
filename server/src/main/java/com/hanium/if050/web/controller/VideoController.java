package com.hanium.if050.web.controller;

import com.hanium.if050.domain.video.Video;
import com.hanium.if050.domain.video.VideoRepository;
import com.hanium.if050.service.video.VideoService;
import com.hanium.if050.web.dto.VideoListDto;
import com.hanium.if050.web.dto.VideoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    private final VideoRepository videoRepository;

    @GetMapping("/vido/{id}/list")
    public ArrayList<VideoListDto> getVideoList(@PathVariable String id) throws JCodecException, IOException {
        ArrayList<Video> videoArrayList = videoRepository.findByCctvId(id);

        ArrayList<VideoListDto> ret = new ArrayList<>();

        for (Video video : videoArrayList) {

            VideoListDto videoListDto = new VideoListDto();

            File file = new File(System.getProperty("user.home") + "/files" + video.getFilePath());
            File thumbnail = null;

            int frameNumber = 0;

            Picture picture = FrameGrab.getFrameFromFile(file, frameNumber);

            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bufferedImage, "png", thumbnail);

            videoListDto.setLocalDate("2021-11-06");
            videoListDto.setSumnail(thumbnail);
            videoListDto.setId(video.getId());

            ret.add(videoListDto);
        }

        return ret;


    }




    @GetMapping("/video/{id}/find")
    public void getVideoId(@PathVariable Long id, HttpServletResponse response) {
        Optional<Video> video = videoRepository.findById(id);


        String saveFileName = System.getProperty("user.home") + "/files" + video.get().getFilePath();

        File file = new File(saveFileName);


        try(
                FileInputStream fis = new FileInputStream(saveFileName);
                OutputStream out = response.getOutputStream();
        ){
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }



    }

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


            String filePath=savePath+"/"+originFileName;
            File save = new File(filePath);
            file.transferTo(save);

            save.setWritable(true);
            save.setReadable(true);

            VideoSaveRequestDto requestDto = new VideoSaveRequestDto();
            requestDto.setCctvId(id);
            requestDto.setFilePath(filePath);
            requestDto.setOriginFileName(originFileName);

            return videoService.saveVideo(requestDto);



        }catch(Exception e){
            System.out.println(e);
            return -1L;
        }


    }

}
