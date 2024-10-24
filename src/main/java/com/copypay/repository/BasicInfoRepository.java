package com.copypay.repository;

import com.copypay.dto.request.*;
import com.copypay.dto.response.*;

import java.util.List;
import java.util.Map;

public interface BasicInfoRepository {
    List<BasicInfoResponse> getBasicInfoList(Map<String, Object> map);
    List<String> getManagerId();
    BasicInfosResponse getBasicInfo(String inputBusinessRegNumber);
    List<MemoResponse> getMemoList(String inputMid);
    ContractResponse getContractByBusinessRegNumber(String inputBusinessRegNumber);
    PaymentMethodResponse getPaymentMethodByNo(int no);
    SettlementInfoResponse getSettlementInfoByNo(int no);
    String getNoByBusinessRegNumber(String businessRegNumber);
    int saveContract(ContractRequest contractRequest);
    int saveSettlementInfo(SettlementInfoRequest settlementInfoRequest);
    int savePaymentMethod(PaymentMethodRequest paymentMethodRequest);
    int saveMemo(MemoRequest memoRequest);
    List<BasicInfoViewResponse> getBasicInfoViewList(BasicInfoViewRequest basicInfoViewRequest);
    int countBasicInfoViewList(BasicInfoViewRequest basicInfoViewRequest);
    int countBasicInfoList(Map<String, Object> map);
}
