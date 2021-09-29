package com.hanium.if050.web.controller;

import com.hanium.if050.service.cctv.CctvService;
import com.hanium.if050.web.dto.CctvSaveRequestDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CctvController {

    private final CctvService cctvService;

    @PostMapping("/cctv/save")
    public String saveCCTV(@RequestBody CctvSaveRequestDto requestDto) {
        return cctvService.cctvSave(requestDto);
    }

    @GetMapping("/cctv/{id}")
    public cctvUrlRequest getURL(@PathVariable String id) {
        String url = cctvService.getUrl(id);
        return new cctvUrlRequest(url);
    }

    @Data
    public class cctvUrlRequest{
        String url;

        cctvUrlRequest(String url) {
            this.url = url;
        }
    }
}
