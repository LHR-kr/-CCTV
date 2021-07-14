package com.hanium.if050.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamingController {

    @GetMapping("/cctv/streaming")
    public String Streaming() {
        return "cctv/streaming";
    }

}
