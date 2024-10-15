package com.copypay.repository.mapper;

import com.copypay.dto.response.ContractProgressListResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ContractProgressMapper {
    List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate);
}
