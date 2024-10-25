package com.copypay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManageIdListResponse {
    private String businessName;       // 상호
    private String businessRegNumber;  // 사업자 번호
    private String ceoName;            // 대표자명
    private String mid;                // MID
}
