package com.copypay.exception;

import org.springframework.http.HttpStatus;

public class BasicInfoNotFoundException extends BaseException{
    private static final String ERROR_MSG = "해당 사업자번호로 등록된 정보가 없습니다.";

    public BasicInfoNotFoundException() {
        super(ERROR_MSG);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
