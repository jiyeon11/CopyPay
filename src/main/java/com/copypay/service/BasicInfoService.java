package com.copypay.service;

import com.copypay.dto.request.ContractRequest;
import com.copypay.dto.response.*;
import com.copypay.exception.*;
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
        ContractResponse contract = basicInfoRepository.getContractByBusinessRegNumber(businessRegNumber);
        if (contract == null) {
            log.error("사업자번호 {}에 대한 기본 정보가 없습니다.", businessRegNumber);
            throw new BusinessRegNumberNotFoundException();
        }
        SettlementInfoResponse settlementInfo = basicInfoRepository.getSettlementInfoByNo(contract.getNo());
        PaymentMethodResponse paymentMethod = basicInfoRepository.getPaymentMethodByNo(contract.getNo());

        if(settlementInfo == null){
            log.error("사업자번호 {}에 대한 정산 정보가 없습니다.", businessRegNumber);
        } else if(paymentMethod == null){
            log.error("사업자번호 {}에 대한 결제수단 정보가 없습니다.", businessRegNumber);
        }

        log.info("사업자번호 {}에 대한 기본 정보를 성공적으로 가져왔습니다.", businessRegNumber);
        return new BasicInfoResponse(contract, settlementInfo, paymentMethod);
    }

    public List<MemoResponse> getMemoList(String inputMid){
        List<MemoResponse> memoList = basicInfoRepository.getMemoList(inputMid);
        if(memoList.isEmpty()) {
            log.info("MID {}에 대한 메모내역이 없습니다.", inputMid);
            throw new MemoNotFoundException();
        }else{
            log.info("총 {}개의 메모를 성공적으로 가져왔습니다", memoList.size());
        }
        return memoList;
    }

    public void updateContract(ContractRequest contractRequest) {
        int rowsAffected = basicInfoRepository.updateContract(contractRequest);
        if (rowsAffected == 0) {
            log.error("사업자번호 : {} 계약 업데이트 실패",contractRequest.getBusinessRegNumber());
            throw new ContractUpdateFailedException();
        }else{
            log.info("사업자번호 : {} 계약 정보가 성공적으로 업데이트 되었습니다.", contractRequest.getBusinessRegNumber());
        }
    }
}
