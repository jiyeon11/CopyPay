package com.copypay.repository;

import com.copypay.dto.request.*;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.dto.response.ManageIdListResponse;
import com.copypay.repository.mapper.SalesManagementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SalesManagementRepositoryImpl implements SalesManagementRepository {
    private final SalesManagementMapper salesManagementMapper;
    
    // 계약 진행현황 조회
    @Override
    public List<ContractProgressListResponse> getContractProgressList(ContractProgressRequest contractProgressRequest) {
        return salesManagementMapper.getContractProgressList(contractProgressRequest);
    }

    // 계약 진행현황 조회 데이터 개수
    @Override
    public int countContractProgressList(ContractProgressRequest contractProgressRequest) {
        return salesManagementMapper.countContractProgressList(contractProgressRequest);
    }

    // 계약 완료현황 조회
    @Override
    public List<ContractDoneListResponse> getContractDoneList(ContractDoneRequest contractDoneRequest) {
        return salesManagementMapper.getContractDoneList(contractDoneRequest);
    }

    // 계약 완료현황 조회 데이터 개수
    @Override
    public int countContractDoneList(ContractDoneRequest contractDoneRequest) {
        return salesManagementMapper.countContractDoneList(contractDoneRequest);
    }

    // 가맹점 ID 관리 조회
    @Override
    public List<ManageIdListResponse> getManageIdList(ManageIdRequest manageIdRequest) {
        return salesManagementMapper.getManageIdList(manageIdRequest);
    }

    // 가맹점 ID 관리 조회 데이터 개수
    @Override
    public int countManageIdList(ManageIdRequest manageIdRequest) {
        return salesManagementMapper.countManageIdList(manageIdRequest);
    }

    // MID 조회
    @Override
    public Optional<String> getMid(String mid){
        return salesManagementMapper.getMid(mid);
    }

    // 사업자 번호 조회
    @Override
    public Optional<String> getRegNumber(String regNumber){
        return salesManagementMapper.getRegNumber(regNumber);
    }

    // MID 발급
    @Override
    public void issueMid(MidIssueRequest midIssueRequest) {
        salesManagementMapper.issueMid(midIssueRequest);
    }

    // 신규 계약 등록
    @Override
    public void registerContract(ContractRegisterRequest contractRegisterRequest) {
        salesManagementMapper.registerContract(contractRegisterRequest);
    }

}
