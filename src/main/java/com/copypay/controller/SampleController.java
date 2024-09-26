package com.copypay.controller;

import com.copypay.dto.request.SampleRequest;
import com.copypay.dto.response.SampleResponse;
import com.copypay.service.SampleReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SampleController {

    private final SampleReaderService sampleService;

    @GetMapping("/")
    public String main() {
        return "main";
    }
    @GetMapping("/basic_info")
    public String basic_info(){
        return "basic-info";
    }
    
    @PostMapping("/sample/{sampleId}/list")
    @ResponseBody
    public SampleResponse samplePostReq(@PathVariable Long sampleId,
                                        @RequestBody @Valid SampleRequest sample) throws Exception {

        return sampleService.getList(sampleId, sample);
    }
}