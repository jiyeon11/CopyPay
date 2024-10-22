package com.copypay.controller;

import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.service.SalesManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = {"/api/contract-done/list/{searchOption}/{searchValue}", "/api/contract-done/list"})
    @ResponseBody
    public ResponseEntity<List<ContractDoneListResponse>> getContractDoneList(@PathVariable(required = false) String searchOption, @PathVariable(required = false) String searchValue){
        return ResponseEntity.ok(salesManagementService.getContractDoneList(searchOption, searchValue));
    }
}
