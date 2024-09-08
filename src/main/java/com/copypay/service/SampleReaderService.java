package com.copypay.service;

import com.copypay.dto.request.SampleRequest;
import com.copypay.dto.response.SampleResponse;
import com.copypay.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SampleReaderService {

    private final SampleRepository sampleRepository;

    public SampleResponse getList(Long sampleId, SampleRequest request){
        List<Map<String, Object>> result = sampleRepository.getList(sampleId, request).orElseThrow(() -> new RuntimeException("조회할 데이터가 없습니다."));
        return SampleResponse.builder()
                .id(sampleId)
                .result(result)
                .build();
    }

}
