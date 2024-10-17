package com.copypay.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> baseExceptionHandler(BaseException e) {
        log.error("BaseException : ", e);
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

    @ExceptionHandler(BusinessRegNumberNotFoundException.class)
    protected ResponseEntity<ErrorResponse> businessRegNumberNotFoundExceptionHandler(BusinessRegNumberNotFoundException e) {
        return ResponseEntity
                .status(ErrorCode.BUSINESS_REG_NUMBER_NOT_FOUND.getStatus().value())
                .body(new ErrorResponse(ErrorCode.BUSINESS_REG_NUMBER_NOT_FOUND));
    }

    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(DataNotFoundException e) {
        return ResponseEntity
                .status(ErrorCode.DATA_NOT_FOUND.getStatus().value())
                .body(new ErrorResponse(ErrorCode.DATA_NOT_FOUND));
    }

    @ExceptionHandler(MemoNotFoundException.class)
    protected ResponseEntity<ErrorResponse> memoNotFoundExceptionHandler(MemoNotFoundException e) {
        return ResponseEntity
                .status(ErrorCode.MEMO_NOT_FOUND.getStatus().value())
                .body(new ErrorResponse(ErrorCode.MEMO_NOT_FOUND));
    }

    @ExceptionHandler(SaveFailedException.class)
    protected ResponseEntity<ErrorResponse> saveFailedExceptionHandler(SaveFailedException e) {
        return ResponseEntity
                .status(ErrorCode.SAVE_FAILED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.SAVE_FAILED));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }
}
