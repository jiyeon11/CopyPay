package com.copypay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractProgressListResponse {
    private String contractDate;       // 계약일자
    private String businessRegNumber;  // 사업자번호
    private String businessName;       // 상호
    private String contractManager;    // 계약담당자
    private String salesManager;       // 영업담당자

}
