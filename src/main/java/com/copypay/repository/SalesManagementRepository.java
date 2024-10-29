package com.copypay.repository;

import com.copypay.dto.request.*;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.dto.response.ManageIdListResponse;

import java.util.List;
import java.util.Optional;

public interface SalesManagementRepository {
    List<ContractProgressListResponse> getContractProgressList(ContractProgressRequest contractProgressRequest);
    List<ContractDoneListResponse> getContractDoneList(ContractDoneRequest contractDoneRequest);
    List<ManageIdListResponse> getManageIdList(ManageIdRequest manageIdRequest);
    int countContractDoneList(ContractDoneRequest contractDoneRequest);
    int countContractProgressList(ContractProgressRequest contractProgressRequest);
    int countManageIdList(ManageIdRequest manageIdRequest);
    Optional<String> getMid(String mid);
    Optional<String> getRegNumber(String regNumber);
    void issueMid(MidIssueRequest midIssueRequest);
    void registerContract(ContractRegisterRequest contractRegisterRequest);
}
