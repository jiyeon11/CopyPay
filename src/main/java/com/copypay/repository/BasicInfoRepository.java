package com.copypay.repository;

import com.copypay.dto.response.BasicInfoListResponse;

import java.util.List;

public interface BasicInfoRepository {
    List<BasicInfoListResponse> getBasicInfoList(String inputMid);
}
