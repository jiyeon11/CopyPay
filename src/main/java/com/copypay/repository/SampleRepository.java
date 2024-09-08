package com.copypay.repository;

import com.copypay.dto.request.SampleRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface SampleRepository {
    Optional<List<Map<String,Object>>> getList(Long sampleId, SampleRequest sampleRequest);
}
