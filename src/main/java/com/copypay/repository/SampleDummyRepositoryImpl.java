package com.copypay.repository;

import com.copypay.dto.request.SampleRequest;
import com.copypay.util.OptionalUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SampleDummyRepositoryImpl implements SampleRepository{


    @Override
    public Optional<List<Map<String, Object>>> getList(Long sampleId, SampleRequest sampleRequest) {

        return OptionalUtil.toOption(
                List.of(
                        Map.of("id",sampleId),
                        Map.of("category","테스트"),
                        Map.of(
                                "data","테스트 데이터",
                                "dataCnt",10,
                                "startDt","20240908",
                                "endDt","99999999"
                        )
                )
        );
    }
}
