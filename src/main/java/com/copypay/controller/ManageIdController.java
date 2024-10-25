package com.copypay.controller;

import com.copypay.dto.response.ManageIdListResponse;
import com.copypay.service.SalesManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class ManageIdController {
    private final SalesManagementService salesManagementService;

    @GetMapping("/manage-id")
    public String manageId() {
        return "manage-id";
    }

    @GetMapping("/api/manage-id/list")
    @ResponseBody
    public ResponseEntity<List<ManageIdListResponse>> getManageIdList(@RequestParam(required = false) String searchOption, @RequestParam(required = false) String searchValue){
        return ResponseEntity.ok(salesManagementService.getManageIdList(searchOption, searchValue));
    }

}