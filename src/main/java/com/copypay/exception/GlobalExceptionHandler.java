package com.copypay.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> BaseExceptionHandler(BaseException e) {
        log.error("BaseException : ", e);
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(BusinessRegNumberNotFoundException.class)
    protected ResponseEntity<ErrorResponse> BusinessRegNumberNotFoundExceptionHandler(BusinessRegNumberNotFoundException e) {
        return ResponseEntity
                .status(ErrorCode.BUSINESS_REG_NUMBER_NOT_FOUND.getStatus().value())
                .body(new ErrorResponse(ErrorCode.BUSINESS_REG_NUMBER_NOT_FOUND));
    }

    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<ErrorResponse> DataNotFoundExceptionHandler(DataNotFoundException e) {
        return ResponseEntity
                .status(ErrorCode.DATA_NOT_FOUND.getStatus().value())
                .body(new ErrorResponse(ErrorCode.DATA_NOT_FOUND));
    }
}
