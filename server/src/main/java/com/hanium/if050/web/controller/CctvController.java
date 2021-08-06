package com.hanium.if050.web.controller;

import com.hanium.if050.service.cctv.CctvService;
import com.hanium.if050.web.dto.CctvSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class CctvController {

    private final CctvService cctvService;

    @PostMapping("/cctv/save")
    public String saveCCTV(@RequestBody CctvSaveRequestDto requestDto) {
        return cctvService.cctvSave(requestDto);
    }
}
