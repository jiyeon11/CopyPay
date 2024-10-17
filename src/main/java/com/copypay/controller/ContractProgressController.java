package com.copypay.controller;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.service.ContractProgressService;
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
    private final ContractProgressService contractProgressService;

    @GetMapping("/contract-progress")
    public String contract_progress(){
        return "contract-progress";
    }

    @GetMapping(value = {"/api/contract-progress/list/{checkedDate}/{startDate}/{endDate}", "/api/contract-progress/list"})
    @ResponseBody
    public ResponseEntity<List<ContractProgressListResponse>> getContractProgressList(@PathVariable(required = false) String checkedDate, @PathVariable(required = false) String startDate, @PathVariable(required = false) String endDate){
        return ResponseEntity.ok(contractProgressService.getContractProgressList(checkedDate, startDate, endDate));
    }

    @PostMapping("/api/contract-progress/register")
    public String contract_progress_register(ContractRegisterRequest contractRegisterRequest){
        contractProgressService.registerContract(contractRegisterRequest);
        return "main";
    }

}
