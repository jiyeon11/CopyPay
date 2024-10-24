package com.copypay.repository;

import com.copypay.dto.request.*;
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
    public List<BasicInfoResponse> getBasicInfoList(String inputMid) {
        return basicInfoMapper.getBasicInfoList(inputMid);
    }

    @Override
    public List<String> getManagerId() {
        return basicInfoMapper.getManagerId();
    }

    @Override
    public BasicInfosResponse getBasicInfo(String inputBusinessRegNumber) {
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
    public int saveContract(ContractRequest contractRequest) {
        return basicInfoMapper.saveContract(contractRequest);
    }

    @Override
    public int saveSettlementInfo(SettlementInfoRequest settlementInfoRequest) {
        return basicInfoMapper.saveSettlementInfo(settlementInfoRequest);
    }

    @Override
    public int savePaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        return basicInfoMapper.savePaymentMethod(paymentMethodRequest);
    }

    @Override
    public int saveMemo(MemoRequest memoRequest) {
        return basicInfoMapper.saveMemo(memoRequest);
    }

    @Override
    public List<BasicInfoViewResponse> getBasicInfoViewList(BasicInfoViewRequest basicInfoViewRequest) {
        return basicInfoMapper.getBasicInfoViewList(basicInfoViewRequest);
    }

    @Override
    public int countBasicInfoViewList(BasicInfoViewRequest basicInfoViewRequest) {
        return basicInfoMapper.countBasicInfoViewList(basicInfoViewRequest);
    }

    @Override
    public int countBasicInfoList(Map<String, Object> map) {
        return basicInfoMapper.countBasicInfoList(map);
    }
}
