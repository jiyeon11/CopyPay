package com.copypay.repository;

import com.copypay.dto.response.BasicInfoListResponse;
import com.copypay.dto.response.BasicInfoResponse;

import java.util.List;

public interface BasicInfoRepository {
    List<BasicInfoListResponse> getBasicInfoList(String inputMid);
    List<String> getManagerId();
    BasicInfoResponse getBasicInfo(String inputBusinessRegNumber);
}
