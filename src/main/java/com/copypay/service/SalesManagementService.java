package com.copypay.service;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.exception.DataNotFoundException;
import com.copypay.repository.SalesManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesManagementService {
    private final SalesManagementRepository salesManagementRepository;

    // 계약 진행현황 조회
    public List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate) {
        List<ContractProgressListResponse> contractProgressList = salesManagementRepository.getContractProgressList(checkedDate, startDate, endDate);
        if(contractProgressList.isEmpty()) {
            log.error("접수일자 {} ~ {}  에 일치하는 정보가 없습니다.", startDate, endDate);
            throw new DataNotFoundException();
        }else{
            log.info("총 {}개의 계약 진행현황 항목을 성공적으로 가져왔습니다", contractProgressList.size());
        }
        return contractProgressList;
    }

    // 계약 완료현황 조회
    public List<ContractDoneListResponse> getContractDoneList(String searchOption, String searchValue) {
        List<ContractDoneListResponse> contractDoneList = salesManagementRepository.getContractDoneList(searchOption, searchValue);
        if(contractDoneList.isEmpty()) {
            log.error("검색 조건 {}에 일치하는 정보가 없습니다. 검색어 : {}", searchOption, searchValue);
            throw new DataNotFoundException();
        }else{
            log.info("총 {}개의 계약 완료현황 항목을 성공적으로 가져왔습니다", contractDoneList.size());
        }
        return contractDoneList;
    }

    // 신규 계약 등록
    public void registerContract(ContractRegisterRequest contractRegisterRequest) {
        salesManagementRepository.registerContract(contractRegisterRequest);
        log.info("신규 계약 등록 완료");
    }

}
