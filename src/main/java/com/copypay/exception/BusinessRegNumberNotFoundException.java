package com.copypay.exception;

public class BusinessRegNumberNotFoundException extends BaseException{

    public BusinessRegNumberNotFoundException() {
        super(ErrorCode.BUSINESS_REG_NUMBER_NOT_FOUND);
    }
}
