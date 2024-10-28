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
public class BasicInfoAPIController {
    private final BasicInfoService basicInfoService;

    @PostMapping("/api/basic-infos")
    public void saveBasicInfo(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(basicInfoService.getUsernameFromSession(request));
        basicInfoService.saveBasicInfo(basicInfosRequest);
    }

    @PostMapping("/api/settlement-infos")
    public void saveSettlementInfo(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(basicInfoService.getUsernameFromSession(request));
        basicInfoService.saveSettlementInfo(basicInfosRequest);
    }

    @PostMapping("/api/payment-methods")
    public void savePaymentMethod(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(basicInfoService.getUsernameFromSession(request));
        basicInfoService.savePaymentMethod(basicInfosRequest);
    }

    @PostMapping("/api/memos")
    public void saveMemo(@Valid @RequestBody MemoRequest memoRequest, HttpServletRequest request){
        memoRequest.setId(basicInfoService.getUsernameFromSession(request));
        basicInfoService.saveMemo(memoRequest);
    }

    @PostMapping("/api/contracts")
    public void saveContract(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(basicInfoService.getUsernameFromSession(request));
        basicInfoService.saveContract(basicInfosRequest);
    }


    @GetMapping("/api/basic-info/list")
    public GenericPaginationResponse<BasicInfoResponse> getBasicInfoList(BasicInfoRequest basicInfoRequest){
        Pagination pagination = basicInfoService.createPagination(basicInfoRequest, basicInfoRequest.getCurrentPage());
        List<BasicInfoResponse> basicInfoResponse = basicInfoService.getBasicInfoList(basicInfoRequest);
        return new GenericPaginationResponse<>(basicInfoResponse, pagination);
    }

    @GetMapping("/api/managers")
    public List<String> getManagerIds(){
        return basicInfoService.getManagerId();
    }

    @GetMapping("/api/basic-info/{businessRegNumber}")
    public BasicInfosResponse getBasicInfo(@PathVariable String businessRegNumber){
        return basicInfoService.getBasicInfo(businessRegNumber);
    }


    @GetMapping("/api/memo/{inputMid}")
    public List<MemoResponse> getMemoList(@PathVariable String inputMid){
        return basicInfoService.getMemoList(inputMid);
    }

    @GetMapping("/api/{businessRegNumber}/no")
    public String getNoByBusinessRegNumber(@PathVariable String businessRegNumber){
        return basicInfoService.getNoByBusinessRegNumber(businessRegNumber);
    }

    @GetMapping("/api/basic-info-view")
    public GenericPaginationResponse<BasicInfoViewResponse> getBasicInfoViewList(@ModelAttribute BasicInfoViewRequest basicInfoViewRequest){
        Pagination pagination = basicInfoService.createPagination(basicInfoViewRequest, basicInfoViewRequest.getCurrentPage());
        List<BasicInfoViewResponse> basicInfoViewResponse = basicInfoService.getBasicInfoViewList(basicInfoViewRequest);
        return new GenericPaginationResponse<>(basicInfoViewResponse, pagination);
    }
}
