package com.copypay.repository;

import com.copypay.dto.request.*;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.dto.response.ManageIdListResponse;

import java.util.List;
import java.util.Optional;

public interface SalesManagementRepository {
    List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate);
    List<ContractDoneListResponse> getContractDoneList(ContractDoneRequest contractDoneRequest);
    int countContractDoneList(ContractDoneRequest contractDoneRequest);
    List<ManageIdListResponse> getManageIdList(String searchOption, String searchValue);
    Optional<String> getMid(String mid);
    Optional<String> getRegNumber(String regNumber);
    void issueMid(MidIssueRequest midIssueRequest);
    void registerContract(ContractRegisterRequest contractRegisterRequest);
}
