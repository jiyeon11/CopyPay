package com.copypay.service;

import com.copypay.dto.request.ContractDoneRequest;
import com.copypay.dto.request.ContractProgressRequest;
import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.request.MidIssueRequest;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.dto.response.ManageIdListResponse;
import com.copypay.exception.BusinessRegNumberNotFoundException;
import com.copypay.exception.DataNotFoundException;
import com.copypay.repository.SalesManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
    public List<ContractProgressListResponse> getContractProgressList(ContractProgressRequest contractProgressRequest) {
        List<ContractProgressListResponse> contractProgressList = salesManagementRepository.getContractProgressList(contractProgressRequest);
        return validateListNotEmpty(
                contractProgressList,
                String.format("총 %d개의 계약 진행현황 항목을 성공적으로 가져왔습니다", contractProgressList.size()),
                String.format("접수일자 %s ~ %s 에 일치하는 정보가 없습니다.", contractProgressRequest.getStartDate(), contractProgressRequest.getEndDate())
        );
    }

    // 계약 완료현황 조회
    public List<ContractDoneListResponse> getContractDoneList(ContractDoneRequest contractDoneRequest){
        List<ContractDoneListResponse> contractDoneList = salesManagementRepository.getContractDoneList(contractDoneRequest);
        return validateListNotEmpty(
                contractDoneList,
                String.format("총 %d개의 기본 정보 항목을 성공적으로 가져왔습니다", contractDoneList.size()),
                "조건에 맞는 기본정보가 없습니다."
        );
    }

    // 가맹점 ID 관리 조회
    public List<ManageIdListResponse> getManageIdList(String searchOption, String searchValue) {
        List<ManageIdListResponse> manageIdList = salesManagementRepository.getManageIdList(searchOption, searchValue);
        return validateListNotEmpty(
                manageIdList,
                String.format("총 %d개의 계약 정보 항목을 성공적으로 가져왔습니다", manageIdList.size()),
                String.format("검색 조건 %s에 일치하는 정보가 없습니다. 검색어 : %s", searchOption, searchValue)
        );
    }

    // MID 조회
    public String getMid(String mid){
        if(Objects.equals(mid, "")) return "MID값을 입력해주세요.";
        if(!mid.endsWith("m")) return "MID의 마지막 글자는 m으로 끝나야 합니다.";
        if(mid.length() > 21) return "MID는 최대 21자리입니다.";
        return salesManagementRepository.getMid(mid)
                .map(midValue -> "사용 불가능한 MID입니다.")
                .orElse("사용 가능한 MID입니다.");
    }

    // 사업자 번호 조회
    public String getRegNumber(String regNumber){
        return salesManagementRepository.getRegNumber(regNumber)
                .map(midValue -> "MID 등록 가능한 사업자번호입니다.")
                .orElse("사업자번호가 존재하지 않거나, 이미 MID가 발급되었습니다.");
    }

    // MID 발급
    @Transactional
    public void issueMid(MidIssueRequest midIssueRequest) {
        try {
            salesManagementRepository.issueMid(midIssueRequest);
            log.info("MID 발급 완료. MID: {}, 사업자번호: {}",
                    midIssueRequest.getMid(),
                    midIssueRequest.getBusinessRegNumber());
        } catch (BusinessRegNumberNotFoundException e) {
            log.error("존재하지 않는 사업자번호입니다.");
            throw new BusinessRegNumberNotFoundException();
        } catch (Exception e) {
            log.error("MID 발급 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("MID 발급에 실패했습니다", e);
        }
    }

    // 신규 계약 등록
    @Transactional
    public void registerContract(ContractRegisterRequest contractRegisterRequest) {
        try {
            salesManagementRepository.registerContract(contractRegisterRequest);
            log.info("신규 계약 등록 완료. 사업자 번호 : {}", contractRegisterRequest.getBusinessRegNumber());
        } catch (Exception e) {
            log.error("신규 계약 등록 실패 : {}", e.getMessage());
            throw new RuntimeException("계약 등록에 실패했습니다", e);
        }
    }

}
