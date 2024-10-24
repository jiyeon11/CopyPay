package com.copypay.exception;

public class LoadFailedException extends BaseException{

    public LoadFailedException() {
        super(ErrorCode.LOAD_FAILED);
    }
}
