package com.copypay.repository;

import com.copypay.dto.response.BasicInfoListResponse;
import com.copypay.dto.response.BasicInfoResponse;
import com.copypay.repository.mapper.BasicInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BasicInfoRepositoryImpl implements BasicInfoRepository {
    private final BasicInfoMapper basicInfoMapper;

    @Override
    public List<BasicInfoListResponse> getBasicInfoList(String inputMid) {
        return basicInfoMapper.getBasicInfoList(inputMid);
    }

    @Override
    public List<String> getManagerId() {
        return basicInfoMapper.getManagerId();
    }

    @Override
    public BasicInfoResponse getBasicInfo(String inputMid) {
        return basicInfoMapper.getBasicInfo(inputMid);
    }
}
