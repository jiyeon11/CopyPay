package com.copypay.exception;

public class MemoNotFoundException extends BaseException{

    public MemoNotFoundException() {
        super(ErrorCode.MEMO_NOT_FOUND);
    }
}
