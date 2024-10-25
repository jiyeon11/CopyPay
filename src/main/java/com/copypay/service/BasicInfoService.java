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

    //기본정보 등록/변경의 조회
    public List<BasicInfoResponse> getBasicInfoList(BasicInfoRequest basicInfoRequest) {
        List<BasicInfoResponse> basicInfoList = basicInfoRepository.getBasicInfoList(basicInfoRequest);
        return salesManagementService.validateListNotEmpty(
                basicInfoList,
                String.format("총 %d개의 기본 정보 항목을 성공적으로 가져왔습니다", basicInfoList.size()),
                String.format("MID %s에 대한 기본 정보가 없습니다.", basicInfoRequest.getMid())
        );
    }

    //담당자 ID 가져오기
    public List<String> getManagerId(){
        List<String> managerIds = basicInfoRepository.getManagerId();
        if(managerIds.isEmpty()){
            log.error("담당자 ID 불러오기에 실패했습니다.");
            throw new LoadFailedException();
        }
        log.info("총 {}개의 담당자 ID를 성공적으로 가져왔습니다.", managerIds.size());
        return managerIds;
    }

    //사업자번호로 기본정보(일반정보, 정산정보, 계약수단) 가져오기
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

    //MID로 메모 내역 가져오기
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

    //일반정보(계약) & 메모 저장
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

    //사업자번호로 NO 가져오기
    public String getNoByBusinessRegNumber(String businessRegNumber){
        return basicInfoRepository.getNoByBusinessRegNumber(businessRegNumber);
    }

    //정산정보 & 메모 저장
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

    //결제수단 & 메모 저장
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

    //메모 저장
    public void saveMemo(MemoRequest memoRequest){
        int rowsAffected = basicInfoRepository.saveMemo(memoRequest);
        if(rowsAffected == 0){
            log.error("MID : {} 메모 저장 실패", memoRequest.getMid());
            throw new SaveFailedException();
        }else{
            log.info("MID : {} 메모가 성공적으로 저장되었습니다.", memoRequest.getMid());
        }
    }

    //기본정보(일반정보, 정산정보, 계약수단 한꺼번에) 저장
    @Transactional
    public void saveBasicInfo(BasicInfosRequest basicInfosRequest){
        int rowsAffected = basicInfoRepository.saveContract(basicInfosRequest.getContractRequest());
        if(rowsAffected != 1){
            log.error("사업자번호 : {} 계약 저장 실패", basicInfosRequest.getContractRequest().getBusinessRegNumber());
            throw new SaveFailedException();
        }

        rowsAffected = basicInfoRepository.saveSettlementInfo(basicInfosRequest.getSettlementInfoRequest());
        if(rowsAffected != 1){
            log.error("NO : {} 정산정보 저장 실패", basicInfosRequest.getSettlementInfoRequest().getNo());
            throw new SaveFailedException();
        }

        rowsAffected = basicInfoRepository.savePaymentMethod(basicInfosRequest.getPaymentMethodRequest());
        if(rowsAffected != 1){
            log.error("NO : {} 결제수단 저장 실패", basicInfosRequest.getPaymentMethodRequest().getNo());
            throw new SaveFailedException();
        }

        rowsAffected = basicInfoRepository.saveMemo(basicInfosRequest.getMemoRequest());
        if(rowsAffected != 1){
            log.error("MID : {} 메모 저장 실패", basicInfosRequest.getMemoRequest().getMid());
            throw new SaveFailedException();
        }

        log.info("사업자번호 : {} 기본정보가 성공적으로 저장되었습니다.", basicInfosRequest.getContractRequest().getBusinessRegNumber());
    }

    //기본정보 조회의 조회
    public List<BasicInfoViewResponse> getBasicInfoViewList(BasicInfoViewRequest basicInfoViewRequest){
        List<BasicInfoViewResponse> list = basicInfoRepository.getBasicInfoViewList(basicInfoViewRequest);
        return salesManagementService.validateListNotEmpty(
                list,
                String.format("총 %d개의 기본 정보 항목을 성공적으로 가져왔습니다", list.size()),
                "조건에 맞는 기본정보가 없습니다."
        );
    }

    //기본정보 등록/변경의 조회에서 리스트 총 개수 가져오기
    public int getBasicInfoViewListTotalCount(BasicInfoViewRequest basicInfoViewRequest){
        return basicInfoRepository.countBasicInfoViewList(basicInfoViewRequest);
    }

    //기본정보 조회의 조회에서 리스트 총 개수 가져오기
    public int getBasicInfoListTotalCount(BasicInfoRequest basicInfoRequest){
        return basicInfoRepository.countBasicInfoList(basicInfoRequest);
    }

    //페이징 설정
    public <T>Pagination createPagination(T request, int currentPage, int totalCount){
        Pagination pagination = new Pagination();
        pagination.setCurrentPageNo(currentPage);
        pagination.setTotalCount(totalCount);
        if (request instanceof BasicInfoRequest) {
            ((BasicInfoRequest) request).setFirstIndex(pagination.getFirstRecordIndex());
            ((BasicInfoRequest) request).setPageSize(pagination.getPageSize());
        } else if (request instanceof BasicInfoViewRequest) {
            ((BasicInfoViewRequest) request).setFirstIndex(pagination.getFirstRecordIndex());
            ((BasicInfoViewRequest) request).setPageSize(pagination.getPageSize());
        }
        return pagination;
    }
}
