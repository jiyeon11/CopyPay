package com.copypay.service;

import com.copypay.dto.response.BasicInfoListResponse;
import com.copypay.dto.response.BasicInfoResponse;
import com.copypay.repository.BasicInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasicInfoService {
    private final BasicInfoRepository basicInfoRepository;

    public List<BasicInfoListResponse> getBasicInfoList(String inputMid) {
        return basicInfoRepository.getBasicInfoList(inputMid);
    }

    public List<String> getManagerId(){
        return basicInfoRepository.getManagerId();
    }

    public BasicInfoResponse getBasicInfo(String inputMid) {
        return basicInfoRepository.getBasicInfo(inputMid);
    }
}
