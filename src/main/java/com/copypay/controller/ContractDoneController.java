package com.copypay.controller;

import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.service.SalesManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContractDoneController {
    private final SalesManagementService salesManagementService;

    @GetMapping("/contract-done")
    public String contractDone() {
        return "contract-done";
    }

    @GetMapping("/api/contract-done/list")
    @ResponseBody
    public ResponseEntity<List<ContractDoneListResponse>> getContractDoneList(@RequestParam(required = false) String searchOption, @RequestParam(required = false) String searchValue){
        return ResponseEntity.ok(salesManagementService.getContractDoneList(searchOption, searchValue));
    }
}
