package com.copypay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicInfoRequest extends PaginationRequest{
    private String mid;
}
