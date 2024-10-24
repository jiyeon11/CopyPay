package com.copypay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ManageIdController {

    @GetMapping("/manage-id")
    public String manageId() {
        return "manage-id";
    }


}
