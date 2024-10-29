package com.copypay.controller;

import com.copypay.dto.Pagination;
import com.copypay.dto.request.ContractProgressRequest;
import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.dto.response.GenericPaginationResponse;
import com.copypay.service.BasicInfoService;
import com.copypay.service.SalesManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ContractProgressController {
    private final SalesManagementService salesManagementService;
    private final BasicInfoService basicInfoService;

    @GetMapping("/contract-progress")
    public String contractProgress() {
        return "contract-progress";
    }

    @GetMapping("/api/contract-progress/list")
    @ResponseBody
    public GenericPaginationResponse<ContractProgressListResponse> getContractProgressList(@ModelAttribute ContractProgressRequest contractProgressRequest) {
        Pagination pagination = basicInfoService.createPagination(contractProgressRequest, contractProgressRequest.getCurrentPage());
        List<ContractProgressListResponse> contractProgressListResponse = salesManagementService.getContractProgressList(contractProgressRequest);
        return new GenericPaginationResponse<>(contractProgressListResponse, pagination);
    }

    @PostMapping("/api/contract-progress/register")
    @ResponseBody
    public ResponseEntity<?> contractProgressRegister(@Valid @RequestBody ContractRegisterRequest contractRegisterRequest) {
        salesManagementService.registerContract(contractRegisterRequest);
        return ResponseEntity.ok().build();
    }
}