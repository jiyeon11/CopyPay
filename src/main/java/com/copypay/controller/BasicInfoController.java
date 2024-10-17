package com.copypay.controller;

import com.copypay.dto.request.ContractRequest;
import com.copypay.dto.request.MemoRequest;
import com.copypay.dto.request.PaymentMethodRequest;
import com.copypay.dto.request.SettlementInfoRequest;
import com.copypay.dto.response.BasicInfoListResponse;
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

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BasicInfoController {
    private final BasicInfoService basicInfoService;

    @GetMapping("/basic-info")
    public String basic_info(){
        return "basic-info";
    }

    @GetMapping(value = {"/api/basic-info/list/{inputMid}", "/api/basic-info/list"})
    @ResponseBody
    public ResponseEntity<List<BasicInfoListResponse>> getBasicInfoList(@PathVariable(required = false) String inputMid){
        return ResponseEntity.ok(basicInfoService.getBasicInfoList(inputMid));
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

    @GetMapping("/api/memo/{inputMid}")
    @ResponseBody
    public ResponseEntity<List<MemoResponse>> getMemoList(@PathVariable String inputMid){
        return ResponseEntity.ok(basicInfoService.getMemoList(inputMid));
    }

    @PutMapping("/api/contracts")
    @ResponseBody
    public ResponseEntity<?> saveContract(@Valid @RequestBody ContractRequest contractRequest){
        basicInfoService.saveContract(contractRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/{businessRegNumber}/no")
    @ResponseBody
    public ResponseEntity<?> getNoByBusinessRegNumber(@PathVariable String businessRegNumber){
        return ResponseEntity.ok().body(basicInfoService.getNoByBusinessRegNumber(businessRegNumber));
    }

    @PostMapping("/api/settlement-infos")
    @ResponseBody
    public ResponseEntity<?> saveSettlementInfo(@Valid @RequestBody SettlementInfoRequest settlementInfoRequest){
        basicInfoService.saveSettlementInfo(settlementInfoRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/payment-methods")
    @ResponseBody
    public ResponseEntity<?> savePaymentMethod(@Valid @RequestBody PaymentMethodRequest paymentMethodRequest){
        basicInfoService.savePaymentMethod(paymentMethodRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/memos")
    @ResponseBody
    public ResponseEntity<?> saveMemo(@Valid @RequestBody MemoRequest memoRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        memoRequest.setId((String)session.getAttribute("username"));
        basicInfoService.saveMemo(memoRequest);
        return ResponseEntity.ok().build();
    }
}
