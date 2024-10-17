package com.copypay.service;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.exception.DataNotFoundException;
import com.copypay.repository.ContractProgressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractProgressService {
    private final ContractProgressRepository contractProgressRepository;

    public List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate) {
        List<ContractProgressListResponse> contractProgressList = contractProgressRepository.getContractProgressList(checkedDate, startDate, endDate);
        if(contractProgressList.isEmpty()) {
            log.error("접수일자 {} ~ {}  에 일치하는 정보가 없습니다.", startDate, endDate);
            throw new DataNotFoundException();
        }else{
            log.info("총 {}개의 계약 진행현황 항목을 성공적으로 가져왔습니다", contractProgressList.size());
        }
        return contractProgressList;
    }

    public void registerContract(ContractRegisterRequest contractRegisterRequest) {
        contractProgressRepository.registerContract(contractRegisterRequest);
    }

}
