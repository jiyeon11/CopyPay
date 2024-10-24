package com.copypay.service;

import com.copypay.dto.Pagination;
import com.copypay.dto.request.*;
import com.copypay.dto.response.*;
import com.copypay.exception.*;
import com.copypay.repository.BasicInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasicInfoService {
    private final BasicInfoRepository basicInfoRepository;
    private final SalesManagementService salesManagementService;

    public List<BasicInfoResponse> getBasicInfoList(Map<String, Object> map) {
        List<BasicInfoResponse> basicInfoList = basicInfoRepository.getBasicInfoList(map);
        return salesManagementService.validateListNotEmpty(
                basicInfoList,
                String.format("총 %d개의 기본 정보 항목을 성공적으로 가져왔습니다", basicInfoList.size()),
                String.format("MID %s에 대한 기본 정보가 없습니다.", map.get("mid"))
        );
    }

    public List<String> getManagerId(){
        List<String> managerIds = basicInfoRepository.getManagerId();
        if(managerIds.isEmpty()){
            log.error("매니저 ID 불러오기에 실패했습니다.");
            throw new LoadFailedException();
        }
        log.info("총 {}개의 매니저 ID를 성공적으로 가져왔습니다.", managerIds.size());
        return managerIds;
    }

    public BasicInfosResponse getBasicInfo(String businessRegNumber){
        ContractResponse contract = basicInfoRepository.getContractByBusinessRegNumber(businessRegNumber);
        if (contract == null) {
            log.error("사업자번호 {}에 대한 기본 정보가 없습니다.", businessRegNumber);
            throw new BusinessRegNumberNotFoundException();
        }
        SettlementInfoResponse settlementInfo = basicInfoRepository.getSettlementInfoByNo(contract.getNo());
        PaymentMethodResponse paymentMethod = basicInfoRepository.getPaymentMethodByNo(contract.getNo());

        if(settlementInfo == null){
            log.info("사업자번호 {}에 대한 정산 정보가 없습니다.", businessRegNumber);
        } else if(paymentMethod == null){
            log.info("사업자번호 {}에 대한 결제수단 정보가 없습니다.", businessRegNumber);
        }

        log.info("사업자번호 {}에 대한 기본 정보를 성공적으로 가져왔습니다.", businessRegNumber);
        return new BasicInfosResponse(contract, settlementInfo, paymentMethod);
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

    @Transactional
    public void saveContract(BasicInfosRequest basicInfosRequest) {
        int rowsAffected = basicInfoRepository.saveContract(basicInfosRequest.getContractRequest());
        rowsAffected += basicInfoRepository.saveMemo(basicInfosRequest.getMemoRequest());
        if(rowsAffected != 2) {
            log.error("사업자번호 : {} 계약 저장 실패", basicInfosRequest.getContractRequest().getBusinessRegNumber());
            throw new SaveFailedException();
        }else{
            log.info("사업자번호 : {} 계약 정보가 성공적으로 저장되었습니다.", basicInfosRequest.getContractRequest().getBusinessRegNumber());
        }
    }

    public String getNoByBusinessRegNumber(String businessRegNumber){
        return basicInfoRepository.getNoByBusinessRegNumber(businessRegNumber);
    }

    @Transactional
    public void saveSettlementInfo(BasicInfosRequest basicInfosRequest){
        int rowsAffected = basicInfoRepository.saveSettlementInfo(basicInfosRequest.getSettlementInfoRequest());
        rowsAffected += basicInfoRepository.saveMemo(basicInfosRequest.getMemoRequest());
        if(rowsAffected <= 2){
            log.error("NO : {} 정산정보 저장 실패", basicInfosRequest.getSettlementInfoRequest().getNo());
            throw new SaveFailedException();
        }else{
            log.info("NO : {} 정산정보 정보가 성공적으로 저장되었습니다.", basicInfosRequest.getSettlementInfoRequest().getNo());
        }
    }

    @Transactional
    public void savePaymentMethod(BasicInfosRequest basicInfosRequest){
        int rowsAffected = basicInfoRepository.savePaymentMethod(basicInfosRequest.getPaymentMethodRequest());
        rowsAffected += basicInfoRepository.saveMemo(basicInfosRequest.getMemoRequest());
        if(rowsAffected <= 2){
            log.error("NO : {} 결제수단 저장 실패", basicInfosRequest.getPaymentMethodRequest().getNo());
            throw new SaveFailedException();
        }else{
            log.info("NO : {} 결제수단이 성공적으로 저장되었습니다.", basicInfosRequest.getPaymentMethodRequest().getNo());
        }
    }

    public void saveMemo(MemoRequest memoRequest){
        int rowsAffected = basicInfoRepository.saveMemo(memoRequest);
        if(rowsAffected == 0){
            log.error("MID : {} 메모 저장 실패", memoRequest.getMid());
            throw new SaveFailedException();
        }else{
            log.info("MID : {} 메모가 성공적으로 저장되었습니다.", memoRequest.getMid());
        }
    }

    @Transactional
    public void saveBasicInfo(BasicInfosRequest basicInfosRequest){
        int rowsAffected = basicInfoRepository.saveContract(basicInfosRequest.getContractRequest());
        rowsAffected += basicInfoRepository.saveSettlementInfo(basicInfosRequest.getSettlementInfoRequest());
        rowsAffected += basicInfoRepository.savePaymentMethod(basicInfosRequest.getPaymentMethodRequest());
        rowsAffected += basicInfoRepository.saveMemo(basicInfosRequest.getMemoRequest());

        if(rowsAffected != 4){
            log.error("사업자번호 : {} 기본정보 저장 실패", basicInfosRequest.getContractRequest().getBusinessRegNumber());
            throw new SaveFailedException();
        }else{
            log.info("사업자번호 : {} 기본정보가 성공적으로 저장되었습니다.", basicInfosRequest.getContractRequest().getBusinessRegNumber());
        }
    }

    public List<BasicInfoViewResponse> getBasicInfoViewList(BasicInfoViewRequest basicInfoViewRequest){
        List<BasicInfoViewResponse> list = basicInfoRepository.getBasicInfoViewList(basicInfoViewRequest);
        if(list.isEmpty()) {
            log.error("조건에 맞는 기본정보가 없습니다.");
            throw new DataNotFoundException();
        }else{
            log.info("총 {}개의 기본정보 리스트를 성공적으로 조회했습니다.", list.size());
        }
        return list;
    }

    public int getBasicInfoViewListTotalCount(BasicInfoViewRequest basicInfoViewRequest){
        return basicInfoRepository.countBasicInfoViewList(basicInfoViewRequest);
    }

    public int getBasicInfoListTotalCount(Map<String, Object> request){
        return basicInfoRepository.countBasicInfoList(request);
    }

}
