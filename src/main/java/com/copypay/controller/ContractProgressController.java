package com.copypay.controller;

import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.service.ContractProgressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println("시작날짜 :  "+startDate);
        System.out.println("마지막날짜 :  "+endDate);
        System.out.println("chk :  "+checkedDate);
        return ResponseEntity.ok(contractProgressService.getContractProgressList(checkedDate, startDate, endDate));
    }

}
