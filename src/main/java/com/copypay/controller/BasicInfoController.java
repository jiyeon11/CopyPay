package com.copypay.controller;

import com.copypay.service.BasicInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BasicInfoController {
    private final BasicInfoService basicInfoService;

    @GetMapping("/basic-info")
    public String basic_info(){
        return "basic-info";
    }

    @GetMapping("/basic-info-view")
    public String basic_info_view() {
        return "basic-info-view";
    }
}