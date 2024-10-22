package com.copypay.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContractRegisterRequest {
    private String applicationPath; // 접수경로
    
    @NotBlank(message = "상호를 입력해주세요.")
    @Size(max = 25, message = "상호는 25자 이내여야 합니다.")
    private String businessName; // 상호
    
    private String businessType; // 사업체 구분
    
    @NotBlank(message = "사업자 번호를 입력해주세요.")
    @Size(min = 10, max = 10, message = "사업자번호는 10자리여야 합니다.")
    private String businessRegNumber; // 사업자 번호
    
    @NotBlank(message = "대표자명을 입력해주세요.")
    @Size(max = 10, message = "대표자명은 10자 이내여야 합니다.")
    private String ceoName; // 대표자명
    
    @NotBlank(message = "대표자 생년월일을 입력해주세요.")
    private String ceoBirth; // 대표자 생년월일
    
    @NotBlank(message = "대표자 HP를 입력해주세요.")
    @Size(min = 11, max = 11, message = "HP는 11자리여야 합니다.")
    private String ceoHp; // 대표자 HP
    
    @NotBlank(message = "대표자 TEL을 입력해주세요.")
    @Size(min = 11, max = 11, message = "TEL은 11자리여야 합니다.")
    private String ceoTel; // 대표자 Tel
    
    @NotBlank(message = "대표자 E-Mail을 입력해주세요.")
    @Email
    @Size(max = 64, message = "이메일은 64자 이내여야 합니다.")
    private String ceoEmail; // 대표자 Email

    @Size(max = 11, message = "FAX는 최대 11자리입니다.")
    private String ceoFax; // 대표자 FAX
    
    @NotBlank(message = "우편번호를 입력해주세요.")
    @Size(min = 5, max = 5, message = "우편번호는 5자리여야 합니다.")
    private String businessPostCode; // 사업장 소재지 우편번호
    
    @NotBlank(message = "주소를 입력해주세요.")
    @Size(max = 45, message = "주소는 45자 이내여야 합니다.")
    private String businessAddressLine1; // 사업장 소재지 주소1

    @NotBlank(message = "상세주소를 입력해주세요.")
    @Size(max = 45, message = "주소는 45자 이내여야 합니다.")
    private String businessAddressLine2; // 사업장 소재지 주소2

    @NotBlank(message = "가맹점 계약담당자명을 입력해주세요.")
    @Size(max = 10, message = "담당자명은 10자 이내여야 합니다.")
    private String franchiseContractManagerName; // 가맹점 계약담당자 이름

    @NotBlank(message = "가맹점 계약담당자 Tel을 입력해주세요.")
    @Size(min = 11, max = 11, message = "TEL은 11자리여야 합니다.")
    private String franchiseContractManagerTel; // 가맹점 계약담당자 Tel

    @NotBlank(message = "가맹점 계약담당자 HP를 입력해주세요.")
    @Size(min = 11, max = 11, message = "HP는 11자리여야 합니다.")
    private String franchiseContractManagerHp; // 가맹점 계약담당자 Hp

    @NotBlank(message = "가맹점 계약담당자 E-Mail을 입력해주세요.")
    @Email
    @Size(max = 64, message = "이메일은 64자 이내여야 합니다.")
    private String franchiseContractManagerEmail; // 가맹점 계약담당자 Email

    @NotBlank(message = "세금계산서 E-Mail을 입력해주세요.")
    @Email
    @Size(max = 64, message = "이메일은 64자 이내여야 합니다.")
    private String taxInvoiceEmail; // 세금계산서 E-Mail

    private String contractManager; // 계약담당자
    private String salesManager; // 영업담당자

    @NotBlank(message = "계약일자를 입력해주세요.")
    private String contractDate; // 계약일자

}
