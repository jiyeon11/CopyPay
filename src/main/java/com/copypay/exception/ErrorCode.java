package com.copypay.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    BUSINESS_REG_NUMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "유효하지 않은 사업자번호입니다."),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "조회 결과가 없습니다."),
    MEMO_NOT_FOUND(HttpStatus.NOT_FOUND, "내역이 없습니다."),
    CONTRACT_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "계약 업데이트에 실패하였습니다.");
    private final HttpStatus status;
    private final String message;
}
