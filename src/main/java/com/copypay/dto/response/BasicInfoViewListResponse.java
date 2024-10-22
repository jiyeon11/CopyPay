package com.copypay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicInfoViewListResponse {
    private String businessRegNumber;  //사업자번호
    private String businessName;  //상호
    private String mid;  //MID
    private String isUsed;  //사용여부
    private String isSmallMidUsed; //영중소 사용여부
    private String contractDate;  //등록일
}