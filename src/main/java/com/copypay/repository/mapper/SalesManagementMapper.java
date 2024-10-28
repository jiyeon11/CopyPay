package com.copypay.repository.mapper;

import com.copypay.dto.request.ContractRegisterRequest;
import com.copypay.dto.request.MidIssueRequest;
import com.copypay.dto.response.ContractDoneListResponse;
import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.dto.response.ManageIdListResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface SalesManagementMapper {
    List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate);
    List<ContractDoneListResponse> getContractDoneList(String searchOption, String searchValue);
    List<ManageIdListResponse> getManageIdList(String searchOption, String searchValue);
    Optional<String> getMid(String mid);
    void issueMid(MidIssueRequest midIssueRequest);
    void registerContract(ContractRegisterRequest contractRegisterRequest);
}
