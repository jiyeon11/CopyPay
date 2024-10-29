package com.copypay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractProgressRequest extends PaginationRequest{
    private String startDate;
    private String endDate;
    private String checkedDate;
}
