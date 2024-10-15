package com.copypay.repository;

import com.copypay.dto.response.ContractProgressListResponse;
import com.copypay.repository.mapper.ContractProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContractProgressImpl implements ContractProgressRepository{
    private final ContractProgressMapper contractProgressMapper;
    @Override
    public List<ContractProgressListResponse> getContractProgressList(String checkedDate, String startDate, String endDate) {
        return contractProgressMapper.getContractProgressList(checkedDate, startDate, endDate);
    }
}
