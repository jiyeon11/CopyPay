package com.copypay.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MidIssueRequest {

    @NotBlank(message = "사업자 번호를 입력해주세요.")
    @Size(min = 10, max = 10, message = "사업자번호는 10자리여야 합니다.")
    private String businessRegNumber; // 사업자 번호

    private String mid;
}