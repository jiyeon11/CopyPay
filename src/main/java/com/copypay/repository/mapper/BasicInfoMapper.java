package com.copypay.repository.mapper;

import com.copypay.dto.request.ContractRequest;
import com.copypay.dto.request.SettlementInfoRequest;
import com.copypay.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasicInfoMapper {
    List<BasicInfoListResponse> getBasicInfoList(String inputMid);
    List<String> getManagerId();
    BasicInfoResponse getBasicInfo(String inputBusinessRegNumber);
    List<MemoResponse> getMemoList(String inputMid);
    ContractResponse getContractByBusinessRegNumber(String inputBusinessRegNumber);
    PaymentMethodResponse getPaymentMethodByNo(int no);
    SettlementInfoResponse getSettlementInfoByNo(int no);
    String getNoByBusinessRegNumber(String businessRegNumber);
    int saveContract(ContractRequest contractRequest);
    int saveSettlementInfo(SettlementInfoRequest settlementInfoRequest);
}
