package com.copypay.controller;

import com.copypay.dto.Pagination;
import com.copypay.dto.request.*;
import com.copypay.dto.response.BasicInfoResponse;
import com.copypay.dto.response.GenericPaginationResponse;
import com.copypay.dto.response.BasicInfoViewResponse;
import com.copypay.dto.response.MemoResponse;
import com.copypay.service.BasicInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BasicInfoController {
    private final BasicInfoService basicInfoService;

    private String getUsernameFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        if (username == null) {
            log.error("사용자 id가 세션에 존재하지 않습니다.");
            throw new IllegalArgumentException("사용자 id가 세션에 존재하지 않습니다.");
        }
        return username;
    }

    @GetMapping("/basic-info")
    public String basic_info(){
        return "basic-info";
    }

    @GetMapping("/basic-info-view")
    public String basic_info_view() {
        return "basic-info-view";
    }

    @GetMapping("/api/basic-info/list")
    @ResponseBody
    public ResponseEntity<GenericPaginationResponse<BasicInfoResponse>> getBasicInfoList(@RequestParam(required = false) String mid,
                                                                                     @RequestParam(required = false, defaultValue = "1") Integer currentPage){

        BasicInfoRequest basicInfoRequest = new BasicInfoRequest(mid);
        Pagination pagination = new Pagination();
        pagination.setCurrentPageNo(currentPage);
        pagination.setTotalCount(basicInfoService.getBasicInfoListTotalCount(basicInfoRequest));
        basicInfoRequest.setFirstIndex(pagination.getFirstRecordIndex());
        basicInfoRequest.setPageSize(pagination.getPageSize());
        List<BasicInfoResponse> basicInfoResponse = basicInfoService.getBasicInfoList(basicInfoRequest);
        GenericPaginationResponse<BasicInfoResponse> response = new GenericPaginationResponse<>(basicInfoResponse, pagination);
        return ResponseEntity.ok().body(response);
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

    @PostMapping("/api/basic-infos")
    @ResponseBody
    public ResponseEntity<?> saveBasicInfo(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(getUsernameFromSession(request));
        basicInfoService.saveBasicInfo(basicInfosRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/memo/{inputMid}")
    @ResponseBody
    public ResponseEntity<List<MemoResponse>> getMemoList(@PathVariable String inputMid){
        return ResponseEntity.ok(basicInfoService.getMemoList(inputMid));
    }

    @PostMapping("/api/contracts")
    @ResponseBody
    public ResponseEntity<?> saveContract(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(getUsernameFromSession(request));
        basicInfoService.saveContract(basicInfosRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/{businessRegNumber}/no")
    @ResponseBody
    public ResponseEntity<?> getNoByBusinessRegNumber(@PathVariable String businessRegNumber){
        return ResponseEntity.ok().body(basicInfoService.getNoByBusinessRegNumber(businessRegNumber));
    }

    @PostMapping("/api/settlement-infos")
    @ResponseBody
    public ResponseEntity<?> saveSettlementInfo(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(getUsernameFromSession(request));
        basicInfoService.saveSettlementInfo(basicInfosRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/payment-methods")
    @ResponseBody
    public ResponseEntity<?> savePaymentMethod(@Valid @RequestBody BasicInfosRequest basicInfosRequest, HttpServletRequest request){
        basicInfosRequest.getMemoRequest().setId(getUsernameFromSession(request));
        basicInfoService.savePaymentMethod(basicInfosRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/memos")
    @ResponseBody
    public ResponseEntity<?> saveMemo(@Valid @RequestBody MemoRequest memoRequest, HttpServletRequest request){
        memoRequest.setId(getUsernameFromSession(request));
        basicInfoService.saveMemo(memoRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/basic-info-view")
    @ResponseBody
    public ResponseEntity<GenericPaginationResponse<BasicInfoViewResponse>> getBasicInfoViewList(@RequestParam(required = false) String mid,
                                                                          @RequestParam(required = false) String businessType,
                                                                          @RequestParam(required = false) String isUsed,
                                                                          @RequestParam(required = false) Boolean dateOption,
                                                                          @RequestParam(required = false) String startDate,
                                                                          @RequestParam(required = false) String endDate,
                                                                          @RequestParam(required = false) String isSmallMidUsed,
                                                                          @RequestParam(required = false, defaultValue = "1") Integer currentPage){
        BasicInfoViewRequest basicInfoViewRequest = new BasicInfoViewRequest(mid, businessType, isUsed, dateOption, startDate, endDate, isSmallMidUsed);
        //페이징
        Pagination pagination = new Pagination();
        pagination.setCurrentPageNo(currentPage);
        pagination.setTotalCount(basicInfoService.getBasicInfoViewListTotalCount(basicInfoViewRequest));
        basicInfoViewRequest.setFirstIndex(pagination.getFirstRecordIndex());
        basicInfoViewRequest.setPageSize(pagination.getPageSize());

        List<BasicInfoViewResponse> basicInfoViewResponse = basicInfoService.getBasicInfoViewList(basicInfoViewRequest);
        GenericPaginationResponse<BasicInfoViewResponse> response = new GenericPaginationResponse<>(basicInfoViewResponse, pagination);

        return ResponseEntity.ok().body(response);
    }
}