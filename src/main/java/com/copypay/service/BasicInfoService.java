package com.copypay.service;

import com.copypay.dto.response.BasicInfoListResponse;
import com.copypay.dto.response.BasicInfoResponse;
import com.copypay.exception.BusinessRegNumberNotFoundException;
import com.copypay.exception.DataNotFoundException;
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
        List<BasicInfoListResponse> basicInfoList = basicInfoRepository.getBasicInfoList(inputMid);
        if(basicInfoList.isEmpty()) {
            log.error("MID {}에 대한 기본 정보가 없습니다.", inputMid);
            throw new DataNotFoundException();
        }else{
            log.info("총 {}개의 기본 정보 항목을 성공적으로 가져왔습니다", basicInfoList.size());
        }
        return basicInfoRepository.getBasicInfoList(inputMid);
    }

    public List<String> getManagerId(){
        List<String> managerIds = basicInfoRepository.getManagerId();
        log.info("총 {}개의 매니저 ID를 성공적으로 가져왔습니다.", managerIds.size());
        return managerIds;
    }

    public BasicInfoResponse getBasicInfo(String businessRegNumber){
        BasicInfoResponse basicInfo = basicInfoRepository.getBasicInfo(businessRegNumber);
        if (basicInfo == null) {
            log.error("사업자번호 {}에 대한 기본 정보가 없습니다.", businessRegNumber);
            throw new BusinessRegNumberNotFoundException();
        }
        log.info("사업자번호 {}에 대한 기본 정보를 성공적으로 가져왔습니다.", businessRegNumber);
        return basicInfo;
    }
}
