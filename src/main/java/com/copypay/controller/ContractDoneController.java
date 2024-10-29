package com.copypay.controller;

import com.copypay.dto.Pagination;
import com.copypay.dto.request.ContractDoneRequest;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.GenericPaginationResponse;
import com.copypay.service.BasicInfoService;
import com.copypay.service.SalesManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContractDoneController {
    private final SalesManagementService salesManagementService;
    private final BasicInfoService basicInfoService;

    @GetMapping("/contract-done")
    public String contractDone() {
        return "contract-done";
    }

    @GetMapping("/api/contract-done/list")
    @ResponseBody
    public GenericPaginationResponse<ContractDoneListResponse> getContractDoneList(@ModelAttribute ContractDoneRequest contractDoneRequest){
        Pagination pagination = basicInfoService.createPagination(contractDoneRequest, contractDoneRequest.getCurrentPage());
        List<ContractDoneListResponse> contractDoneListResponse = salesManagementService.getContractDoneList(contractDoneRequest);
        return new GenericPaginationResponse<>(contractDoneListResponse, pagination);
    }
}
