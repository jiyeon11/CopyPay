package com.copypay.exception;

public class SaveFailedException extends BaseException{

    public SaveFailedException() {
        super(ErrorCode.SAVE_FAILED);
    }
}
