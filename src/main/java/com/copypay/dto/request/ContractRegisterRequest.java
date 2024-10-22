package com.copypay.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContractRegisterRequest {
    private String applicationPath; // 접수경로
    @NotBlank(message = "상호를 입력해주세요.")
    private String businessName; // 상호
    private String businessType; // 사업체 구분
    @NotBlank(message = "사업자 번호를 입력해주세요.")
    private String businessRegNumber; // 사업자 번호
    @NotBlank(message = "대표자명을 입력해주세요.")
    private String ceoName; //
    @NotBlank(message = "대표자 생년월일을 입력해주세요.")
    private String ceoBirth; // 대표자 생년월일
    @NotBlank(message = "대표자 HP를 입력해주세요.")
    private String ceoHp; // 대표자 HP
    @NotBlank(message = "대표자 TEL을 입력해주세요.")
    private String ceoTel; // 대표자 Tel
    @NotBlank(message = "대표자 E-Mail을 입력해주세요.")
    private String ceoEmail; // 대표자 Email
    private String ceoFax; // 대표자 FAX
    @NotBlank(message = "사업장 소재지를 입력해주세요.")
    private String businessPostCode; // 사업장 소재지 우편번호
    @NotBlank(message = "사업장 소재지를 입력해주세요.")
    private String businessAddressLine1; // 사업장 소재지 주소1
    @NotBlank(message = "사업장 소재지를 입력해주세요.")
    private String businessAddressLine2; // 사업장 소재지 주소2
    @NotBlank(message = "가맹점 계약담당자명을 입력해주세요.")
    private String franchiseContractManagerName; // 가맹점 계약담당자 이름
    @NotBlank(message = "가맹점 계약담당자 Tel을 입력해주세요.")
    private String franchiseContractManagerTel; // 가맹점 계약담당자 Tel
    @NotBlank(message = "가맹점 계약담당자 HP를 입력해주세요.")
    private String franchiseContractManagerHp; // 가맹점 계약담당자 Hp
    @NotBlank(message = "가맹점 계약담당자 E-Mail을 입력해주세요.")
    private String franchiseContractManagerEmail; // 가맹점 계약담당자 Email
    @NotBlank(message = "세금계산서 E-Mail을 입력해주세요.")
    private String taxInvoiceEmail; // 세금계산서 E-Mail
    private String contractManager; // 계약담당자
    private String salesManager; // 영업담당자
    @NotBlank(message = "계약일자를 입력해주세요.")
    private String contractDate; // 계약일자
}
