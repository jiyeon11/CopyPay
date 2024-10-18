package com.copypay.repository;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.repository.mapper.SalesManagementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalesManagementRepositoryImpl implements SalesManagementRepository {
    private final SalesManagementMapper salesManagementMapper;
    
    // 계약 진행현황 조회
    @Override
    public List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate) {
        return salesManagementMapper.getContractProgressList(checkedDate, startDate, endDate);
    }

    // 신규 계약 등록
    @Override
    public void registerContract(ContractRegisterRequest contractRegisterRequest) {
        salesManagementMapper.registerContract(contractRegisterRequest);
    }
}
