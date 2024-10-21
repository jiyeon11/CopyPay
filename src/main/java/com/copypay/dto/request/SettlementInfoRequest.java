package com.copypay.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettlementInfoRequest {
    private int no;
    private String paymentMethod;  //지급방법
    private String feePaymentMethod;  //수수료 지급방법
    @Size(min = 8, max = 8, message = "적용시작일자가 올바르지 않습니다.")
    private String feeSettlementDate;  //수수료 정산일자
    private String depositAccountFormat;  //정산 입금 통장 인자 표기 방법
    private String accountParameterName;  //통장 인자명
    private String taxInvoiceCriteria;  //세금계산서 발행 기준
    private String taxInvoiceVat;  //세금계산서 부가세 계산 방식
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String taxInvoiceEmail;  //세금계산서 이메일
    private String partialCancellationAllowed;  //부분취소 가능 여부
}