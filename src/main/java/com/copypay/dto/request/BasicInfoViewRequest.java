package com.copypay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoViewRequest extends PaginationRequest{
    private String mid;  //MID
    private String businessType;  //사업체구분
    private String isUsed;  //사용여부
    private Boolean dateOption;  //날짜 체크여부
    private String startDate;  //시작일
    private String endDate;  //마지막일
    private String isSmallMidUsed;  //영중소 사용여부
}
