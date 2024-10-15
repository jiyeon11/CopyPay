package com.copypay.controller;

import com.copypay.dto.response.BasicInfoListResponse;
import com.copypay.dto.response.MemoResponse;
import com.copypay.service.BasicInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BasicInfoController {
    private final BasicInfoService basicInfoService;

    @GetMapping("/basic-info")
    public String basic_info(){
        return "basic-info";
    }

    @GetMapping(value = {"/api/basic-info/list/{inputMid}", "/api/basic-info/list"})
    @ResponseBody
    public ResponseEntity<List<BasicInfoListResponse>> getBasicInfoList(@PathVariable(required = false) String inputMid){
        return ResponseEntity.ok(basicInfoService.getBasicInfoList(inputMid));
    }
    
    @GetMapping("/api/managers")
    @ResponseBody
    public ResponseEntity<?> getManagerIds(){
        return ResponseEntity.ok(basicInfoService.getManagerId());
    }

    @GetMapping("/api/basic-info/{businessRegNumber}")
    @ResponseBody
    public ResponseEntity<?> getBasicInfo(@PathVariable String businessRegNumber){
        return ResponseEntity.ok(basicInfoService.getBasicInfo(businessRegNumber));
    }

    @GetMapping("/api/memo/{inputMid}")
    @ResponseBody
    public ResponseEntity<List<MemoResponse>> getMemoList(@PathVariable String inputMid){
        return ResponseEntity.ok(basicInfoService.getMemoList(inputMid));
    }
}
