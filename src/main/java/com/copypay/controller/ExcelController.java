package com.copypay.controller;

import com.copypay.dto.request.ContractProgressRequest;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.service.ExcelService;
import com.copypay.service.SalesManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.io.ByteArrayInputStream;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ExcelController {
    private final SalesManagementService salesManagementService;
    private final ExcelService excelService;

    @GetMapping("/api/contract-progress/excel")
    @ResponseBody
    public ResponseEntity<byte[]>  getContractProgressExcel(@ModelAttribute ContractProgressRequest contractProgressRequest) {
        contractProgressRequest.setPageSize(Integer.MAX_VALUE);
        List<ContractProgressListResponse> contractProgressListResponse = salesManagementService.getContractProgressList(contractProgressRequest);
        ByteArrayInputStream excelData = excelService.generateExcel(contractProgressListResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=contract_progress.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData.readAllBytes());
    }

}
