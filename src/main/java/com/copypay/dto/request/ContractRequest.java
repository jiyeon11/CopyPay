package com.copypay.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {
    @NotBlank(message = "사업자번호를 입력해주세요.")
    private String businessRegNumber;  //사업자번호

    @NotBlank(message = "MID를 입력해주세요.")
    private String mid;  //MID

    private String businessName;  //상호

    @Size(min = 8, max = 8, message = "계약일자가 올바르지 않습니다.")
    private String contractDate;  //계약일자

    private String ceoName;  //대표자명

    @NotBlank(message = "대표 TEL을 입력해주세요.")
    @Size(min = 11, max = 11, message = "전화번호는 11자리여야 합니다.")
    private String ceoTel;  //대표 TEL

    @NotBlank(message = "대표 E-Mail을 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String ceoEmail;  //대표 E-Mail

    @Size(max = 11, message = "FAX는 최대 11자리입니다.")
    private String ceoFax;  //대표 FAX

    private String businessPostcode;  //사업장 우편번호

    private String businessAddressLine1;  //사업장 주소1

    private String businessAddressLine2;  //사업장 주소2

    @NotBlank(message = "가맹점 계약담당 성명을 입력해주세요.")
    private String franchiseContractManagerName;  //가맹점 계약담당 성명

    @NotBlank(message = "가맹점 계약담당 TEL을 입력해주세요.")
    @Size(min = 11, max = 11, message = "전화번호는 11자리여야 합니다.")
    private String franchiseContractManagerTel;//가맹점 계약담당 TEL

    @NotBlank(message = "가맹점 계약담당 HP를 입력해주세요.")
    @Size(min = 11, max = 11, message = "전화번호는 11자리여야 합니다.")
    private String franchiseContractManagerHp;//가맹점 계약담당 HP

    @NotBlank(message = "가맹점 계약담당 이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String franchiseContractManagerEmail;//가맹점 계약담당 EMail

    private String applicationPath;  //접수경로

    private String contractManager;  //계약담당자

    private String salesManager;  //영업담당자
}
