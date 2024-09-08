package com.copypay.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class SampleResponse {

    private Long id;
    private List<Map<String, Object>> result;

}
