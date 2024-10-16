package com.copypay.repository;

import com.copypay.dto.request.ContractRequest;
import com.copypay.dto.request.SettlementInfoRequest;
import com.copypay.dto.response.*;
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
    public BasicInfoResponse getBasicInfo(String inputBusinessRegNumber) {
        return basicInfoMapper.getBasicInfo(inputBusinessRegNumber);
    }

    @Override
    public List<MemoResponse> getMemoList(String inputMid) {
        return basicInfoMapper.getMemoList(inputMid);
    }

    @Override
    public ContractResponse getContractByBusinessRegNumber(String inputBusinessRegNumber) {
        return basicInfoMapper.getContractByBusinessRegNumber(inputBusinessRegNumber);
    }

    @Override
    public PaymentMethodResponse getPaymentMethodByNo(int no) {
        return basicInfoMapper.getPaymentMethodByNo(no);
    }

    @Override
    public SettlementInfoResponse getSettlementInfoByNo(int no) {
        return basicInfoMapper.getSettlementInfoByNo(no);
    }

    @Override
    public String getNoByBusinessRegNumber(String businessRegNumber) {
        return basicInfoMapper.getNoByBusinessRegNumber(businessRegNumber);
    }

    @Override
    public int updateContract(ContractRequest contractRequest) {
        return basicInfoMapper.updateContract(contractRequest);
    }

}
