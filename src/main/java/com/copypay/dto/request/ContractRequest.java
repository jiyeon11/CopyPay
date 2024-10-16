package com.copypay.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {
    private String businessRegNumber;  //사업자번호
    private String mid;  //MID
    private String businessName;  //상호
    private String contractDate;  //계약일자
    private String ceoName;  //대표자명
    private String ceoTel;  //대표 TEL
    private String ceoEmail;  //대표 E-Mail
    private String ceoFax;  //대표 FAX
    private String businessPostcode;  //사업장 우편번호
    private String businessAddressLine1;  //사업장 주소1
    private String businessAddressLine2;  //사업장 주소2
    private String franchiseContractManagerName;  //가맹점 계약담당 성명
    private String franchiseContractManagerTel;//가맹점 계약담당 TEL
    private String franchiseContractManagerHp;//가맹점 계약담당 HP
    private String franchiseContractManagerEmail;//가맹점 계약담당 EMail
    private String applicationPath;  //접수경로
    private String contractManager;  //계약담당자
    private String salesManager;  //영업담당자
}
