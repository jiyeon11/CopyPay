package com.copypay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BasicInfoRequest extends PaginationRequest{
    private String mid;
}
