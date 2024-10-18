package com.copypay.repository;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractProgressListResponse;
import java.util.List;

public interface SalesManagementRepository {
    List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate);
    void registerContract(ContractRegisterRequest contractRegisterRequest);
}
