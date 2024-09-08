package com.copypay.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends BaseException{
    private static final String ERROR_MSG = "찾는 데이터가 존재하지 않습니다";

    public DataNotFoundException() {
        super(ERROR_MSG);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
