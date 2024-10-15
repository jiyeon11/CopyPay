package com.copypay.repository;

import com.copypay.dto.response.ContractProgressListResponse;
import java.util.List;

public interface ContractProgressRepository {
    List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate);
}
