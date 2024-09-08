package com.copypay.dto.response;

import com.copypay.util.OptionalUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.copypay.util.OptionalUtil.*;

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = toOption(validation)
                .filter( v -> !v.isEmpty())
                .orElse(new HashMap<>());
    }

    public void addValidation(String field, String defaultMessage) {
        validation.put(field, defaultMessage);
    }
}
