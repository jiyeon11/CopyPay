package com.copypay.repository.mapper;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SalesManagementMapper {
    List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate);
    List<ContractDoneListResponse> getContractDoneList(String searchOption, String searchValue);
    void registerContract(ContractRegisterRequest contractRegisterRequest);
}
