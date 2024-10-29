package com.copypay.controller;

import com.copypay.dto.Pagination;
import com.copypay.dto.request.ManageIdRequest;
import com.copypay.dto.request.MidIssueRequest;
import com.copypay.dto.response.GenericPaginationResponse;
import com.copypay.dto.response.ManageIdListResponse;
import com.copypay.service.BasicInfoService;
import com.copypay.service.SalesManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class ManageIdController {
    private final SalesManagementService salesManagementService;
    private final BasicInfoService basicInfoService;

    @GetMapping("/manage-id")
    public String manageId() {
        return "manage-id";
    }

    @GetMapping("/api/manage-id/list")
    @ResponseBody
    public GenericPaginationResponse<ManageIdListResponse> getManageIdList(@ModelAttribute ManageIdRequest manageIdRequest){
        Pagination pagination = basicInfoService.createPagination(manageIdRequest, manageIdRequest.getCurrentPage());
        List<ManageIdListResponse> manageIdListResponse = salesManagementService.getManageIdList(manageIdRequest);
        return new GenericPaginationResponse<>(manageIdListResponse, pagination);
    }

    @GetMapping("/api/manage-id/check-mid")
    @ResponseBody
    public String checkMid(@RequestParam String mid){
        return salesManagementService.getMid(mid);
    }

    @GetMapping("/api/manage-id/check-reg-number")
    @ResponseBody
    public String checkRegNumber(@RequestParam String regNumber){
        return salesManagementService.getRegNumber(regNumber);
    }

    @PostMapping("/api/manage-id/issue-mid")
    @ResponseBody
    public ResponseEntity<?>  issueMid(@Valid @RequestBody MidIssueRequest midIssueRequest){
        salesManagementService.issueMid(midIssueRequest);
        return ResponseEntity.ok().build();
    }

}