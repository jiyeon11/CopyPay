package com.copypay.exception;

public class UpdateFailedException extends BaseException{

    public UpdateFailedException() {
        super(ErrorCode.UPDATE_FAILED);
    }
}
