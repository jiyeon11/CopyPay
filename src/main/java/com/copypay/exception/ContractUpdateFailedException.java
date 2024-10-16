package com.copypay.exception;

public class ContractUpdateFailedException extends BaseException{

    public ContractUpdateFailedException() {
        super(ErrorCode.CONTRACT_UPDATE_FAILED);
    }
}
