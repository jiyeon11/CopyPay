package com.copypay.repository.mapper;

import com.copypay.dto.response.BasicInfoListResponse;
import com.copypay.dto.response.BasicInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasicInfoMapper {
    List<BasicInfoListResponse> getBasicInfoList(String inputMid);
    List<String> getManagerId();
    BasicInfoResponse getBasicInfo(String inputMid);
}
