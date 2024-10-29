package com.copypay.controller;

import com.copypay.dto.Pagination;
import com.copypay.dto.request.BasicInfoRequest;
import com.copypay.dto.request.BasicInfoViewRequest;
import com.copypay.dto.request.BasicInfosRequest;
import com.copypay.dto.request.MemoRequest;
import com.copypay.dto.response.*;
import com.copypay.service.BasicInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class BasicInfoAPIController {
    private final BasicInfoService basicInfoService;

    @PostMapping("/basic-infos")
    public void saveBasicInfo(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfoService.setUsernameFromSession(basicInfosRequest.getMemoRequest(), request);
        basicInfoService.saveBasicInfo(basicInfosRequest);
    }

    @PostMapping("/settlement-infos")
    public void saveSettlementInfo(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfoService.setUsernameFromSession(basicInfosRequest.getMemoRequest(), request);
        basicInfoService.saveSettlementInfo(basicInfosRequest);
    }

    @PostMapping("/payment-methods")
    public void savePaymentMethod(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfoService.setUsernameFromSession(basicInfosRequest.getMemoRequest(), request);
        basicInfoService.savePaymentMethod(basicInfosRequest);
    }

    @PostMapping("/memos")
    public void saveMemo(@Valid @RequestBody MemoRequest memoRequest, HttpServletRequest request){
        basicInfoService.setUsernameFromSession(memoRequest, request);
        basicInfoService.saveMemo(memoRequest);
    }

    @PostMapping("/contracts")
    public void saveContract(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfoService.setUsernameFromSession(basicInfosRequest.getMemoRequest(), request);
        basicInfoService.saveContract(basicInfosRequest);
    }

    @GetMapping("/basic-info/list")
    public GenericPaginationResponse<BasicInfoResponse> getBasicInfoList(BasicInfoRequest basicInfoRequest){
        Pagination pagination = basicInfoService.createPagination(basicInfoRequest, basicInfoRequest.getCurrentPage());
        List<BasicInfoResponse> basicInfoResponse = basicInfoService.getBasicInfoList(basicInfoRequest);
        return new GenericPaginationResponse<>(basicInfoResponse, pagination);
    }

    @GetMapping("/managers")
    public List<String> getManagerIds(){
        return basicInfoService.getManagerId();
    }

    @GetMapping("/basic-info/{businessRegNumber}")
    public BasicInfosResponse getBasicInfo(@PathVariable String businessRegNumber){
        return basicInfoService.getBasicInfo(businessRegNumber);
    }

    @GetMapping("/memo/{inputMid}")
    public List<MemoResponse> getMemoList(@PathVariable String inputMid){
        return basicInfoService.getMemoList(inputMid);
    }

    @GetMapping("/{businessRegNumber}/no")
    public String getNoByBusinessRegNumber(@PathVariable String businessRegNumber){
        return basicInfoService.getNoByBusinessRegNumber(businessRegNumber);
    }

    @GetMapping("/basic-info-view")
    public GenericPaginationResponse<BasicInfoViewResponse> getBasicInfoViewList(@ModelAttribute BasicInfoViewRequest basicInfoViewRequest){
        Pagination pagination = basicInfoService.createPagination(basicInfoViewRequest, basicInfoViewRequest.getCurrentPage());
        List<BasicInfoViewResponse> basicInfoViewResponse = basicInfoService.getBasicInfoViewList(basicInfoViewRequest);
        return new GenericPaginationResponse<>(basicInfoViewResponse, pagination);
    }
}
