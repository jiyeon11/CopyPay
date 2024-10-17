package com.copypay.dto.request;

import lombok.Data;

@Data
public class ContractRegisterRequest {
    private String applicationPath; // 접수경로
    private String businessName; // 상호
    private String businessType; // 사업체 구분
    private String businessRegNumber; // 사업자 번호
    private String ceoName; //
    private String ceoBirth; // 대표자 생년월일
    private String ceoHp; // 대표자 HP
    private String ceoTel; // 대표자 Tel
    private String ceoEmail; // 대표자 Email
    private String ceoFax; // 대표자 FAX
    private String businessPostCode; // 사업장 소재지 우편번호
    private String businessAddressLine1; // 사업장 소재지 주소1
    private String businessAddressLine2; // 사업장 소재지 주소2
    private String franchiseContractManagerName; // 가맹점 계약담당자 이름
    private String franchiseContractManagerTel; // 가맹점 계약담당자 Tel
    private String franchiseContractManagerHp; // 가맹점 계약담당자 Hp
    private String franchiseContractManagerEmail; // 가맹점 계약담당자 Email
    private String taxInvoiceEmail; // 세금계산서 E-Mail
    private String contractManager; // 계약담당자
    private String salesManager; // 영업담당자
    private String contractDate; // 계약일자
}
