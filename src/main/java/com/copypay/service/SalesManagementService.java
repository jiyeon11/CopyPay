package com.copypay.service;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.exception.DataNotFoundException;
import com.copypay.repository.SalesManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesManagementService {
    private final SalesManagementRepository salesManagementRepository;

    // 조회 목록이 비었는지 확인
    public <T> List<T> validateListNotEmpty(List<T> list, String successMessage, String errorMessage) {
        if (list.isEmpty()) {
            log.error(errorMessage);
            throw new DataNotFoundException();
        } else {
            log.info(successMessage, list.size());
        }
        return list;
    }

    // 계약 진행현황 조회
    public List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate) {
        List<ContractProgressListResponse> contractProgressList = salesManagementRepository.getContractProgressList(checkedDate, startDate, endDate);
        return validateListNotEmpty(
                contractProgressList,
                String.format("총 %d개의 계약 진행현황 항목을 성공적으로 가져왔습니다", contractProgressList.size()),
                String.format("접수일자 %s ~ %s 에 일치하는 정보가 없습니다.", startDate, endDate)
        );
    }

    // 계약 완료현황 조회
    public List<ContractDoneListResponse> getContractDoneList(String searchOption, String searchValue) {
        List<ContractDoneListResponse> contractDoneList = salesManagementRepository.getContractDoneList(searchOption, searchValue);
        return validateListNotEmpty(
                contractDoneList,
                String.format("총 %d개의 계약 완료현황 항목을 성공적으로 가져왔습니다", contractDoneList.size()),
                String.format("검색 조건 %s에 일치하는 정보가 없습니다. 검색어 : %s", searchOption, searchValue)
        );
    }

    // 신규 계약 등록
    @Transactional
    public void registerContract(ContractRegisterRequest contractRegisterRequest) {
        salesManagementRepository.registerContract(contractRegisterRequest);
        log.info("신규 계약 등록 완료");
    }

}
